package mate.academy.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.dao.CarDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Car;
import mate.academy.model.Driver;
import mate.academy.model.Manufacturer;
import mate.academy.util.ConnectionUtil;

@Dao
public class CarDaoJdbc implements CarDao {
    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars (model, manufacturer_id) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query, Statement
                        .RETURN_GENERATED_KEYS)) {
            statement.setString(1, car.getModel());
            statement.setLong(2, car.getManufacturer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getObject(1, Long.class));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Could not create car " + car, e);
        }
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {
        String query = "SELECT c.id, c.model, c.manufacturer_id, "
                + "m.name, m.country "
                + "FROM cars c "
                + "JOIN manufacturer m ON c.manufacturer_id = m.id "
                + "WHERE c.id = ? "
                + "AND c.deleted = FALSE";
        Car car = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                car = createCar(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Could not get car by id " + id, e);
        }
        if (car != null) {
            car.setDrivers(getDriversForCar(car.getId()));
        }
        return Optional.ofNullable(car);
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT c.id, c.model, c.manufacturer_id, "
                + "m.name, m.country "
                + "FROM cars c "
                + "JOIN manufacturer m ON c.manufacturer_id = m.id "
                + "WHERE c.deleted = FALSE";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cars.add(createCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Could not get all cars ", e);
        }
        for (Car car : cars) {
            car.setDrivers(getDriversForCar(car.getId()));
        }
        return cars;
    }

    @Override
    public Car update(Car car) {
        String query = "UPDATE cars SET model = ?, "
                + "manufacturer_id = ? WHERE id = ? "
                + "AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, car.getModel());
            statement.setLong(2, car.getManufacturer().getId());
            statement.setLong(3, car.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Could update car " + car, e);
        }
        deleteAllDriversFromCar(car);
        addDriversToCar(car);
        return car;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE cars SET deleted = TRUE "
                + "WHERE id = ? ";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            int delete = statement.executeUpdate();
            return delete > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Could not delete car by id " + id, e);
        }
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT c.id, c.model, c.manufacturer_id, "
                + "m.name, m.country "
                + "FROM cars c "
                + "JOIN cars_drivers cd ON c.id = cd.id_car "
                + "JOIN manufacturer m ON c.manufacturer_id = m.id "
                + "JOIN drivers d ON d.id = cd.id_driver "
                + "WHERE cd.id_driver = ? "
                + "AND d.deleted = FALSE "
                + "AND c.deleted = FALSE";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, driverId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cars.add(createCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Could not get all by Driver id " + driverId, e);
        }
        for (Car car : cars) {
            car.setDrivers(getDriversForCar(car.getId()));
        }
        return cars;
    }

    private void addDriversToCar(Car car) {
        String query = "INSERT INTO cars_drivers (id_car, id_driver) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            for (Driver driver : car.getDrivers()) {
                statement.setLong(1, car.getId());
                statement.setLong(2, driver.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Could not add driver "
                    + "to car " + car, e);
        }
    }

    private void deleteAllDriversFromCar(Car car) {
        String query = "DELETE FROM cars_drivers WHERE id_car = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, car.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Could not delete driver "
                    + " from car " + car, e);
        }
    }

    private Car createCar(ResultSet resultSet) throws SQLException {
        Long idCar = resultSet.getObject("id", Long.class);
        String modelCar = resultSet.getString("model");
        Long manufacturerIdCar = resultSet.getObject("manufacturer_id", Long.class);
        String nameManufacturer = resultSet.getString("name");
        String countryManufacturer = resultSet.getString("country");
        Manufacturer manufacturer = new Manufacturer(nameManufacturer, countryManufacturer);
        manufacturer.setId(manufacturerIdCar);
        Car car = new Car(modelCar, manufacturer);
        car.setId(idCar);
        return car;
    }

    private List<Driver> getDriversForCar(Long idCar) {
        String query = "SELECT d.name_driver, d.licence_number, "
                + "cd.id_driver "
                + "FROM cars_drivers cd "
                + "JOIN drivers d ON cd.id_driver = d.id "
                + "WHERE cd.id_car = ? AND d.beleted = FALSE";
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, idCar);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name_driver");
                String licenceNumber = resultSet.getString("licence_number");
                Long idDriver = resultSet.getObject("id_driver", Long.class);
                Driver driver = new Driver(name, licenceNumber);
                driver.setId(idDriver);
                drivers.add(driver);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Could not get drivers from Car", e);
        }
        return drivers;
    }
}
