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
        String query = "INSERT INTO cars (model_car, manufacturer_id) VALUES (?, ?);";
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
        String query = "SELECT cars.id_car, cars.model_car, cars.manufacturer_id, "
                + "manufacturer.name_manufacturer, manufacturer.country_manufacturer "
                + "FROM cars "
                + "JOIN manufacturer ON cars.manufacturer_id = manufacturer.id_manufacturer "
                + "WHERE id_car = ? "
                + "AND delete_car = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(createCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Could not get car by id " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT cars.id_car, cars.model_car, cars.manufacturer_id, "
                + "manufacturer.name_manufacturer, manufacturer.country_manufacturer "
                + "FROM cars "
                + "JOIN manufacturer ON cars.manufacturer_id = manufacturer.id_manufacturer "
                + "WHERE delete_car = FALSE";
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
        return cars;
    }

    @Override
    public Car update(Car car) {
        String query = "UPDATE cars SET model_car = ?, "
                + "manufacturer_id = ? WHERE id_car = ? "
                + "AND delete_car = FALSE";
        deleteAllDriversFromCar(car);
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, car.getModel());
            statement.setLong(2, car.getManufacturer().getId());
            statement.setLong(3, car.getId());
            statement.executeUpdate();
            for (Driver driver : car.getDrivers()) {
                addDriverToCar(driver, car);
            }
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Could update car " + car, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE cars SET delete_car = TRUE "
                + "WHERE id_car = ? ";
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
        String query = "SELECT cars.id_car, cars.model_car, cars.manufacturer_id, "
                + "manufacturer.name_manufacturer, manufacturer.country_manufacturer "
                + "FROM cars "
                + "JOIN cars_drivers ON cars.id_car = cars_drivers.id_car "
                + "JOIN manufacturer ON cars.manufacturer_id = manufacturer.id_manufacturer "
                + "WHERE cars_drivers.id_driver = ? "
                + "AND delete_driver = FALSE "
                + "AND delete_car = FALSE";
        List<Car> cars = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, driverId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cars.add(createCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Could not get all Cars by Driver id " + driverId, e);
        }
        return cars;
    }

    private void addDriverToCar(Driver driver, Car car) {
        String query = "INSERT INTO cars_drivers (id_car, id_driver) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, car.getId());
            statement.setLong(2, driver.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Could not add driver "
                    + driver + "to car " + car, e);
        }
    }

    private void deleteAllDriversFromCar(Car car) {
        String query = "DELETE FROM `cars_drivers` WHERE id_car = ?";
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
        Long idCar = resultSet.getObject("id_car", long.class);
        String modelCar = resultSet.getString("model_car");
        Long manufacturerIdCar = resultSet.getObject("manufacturer_id", long.class);
        String nameManufacturer = resultSet.getString("name_manufacturer");
        String countryManufacturer = resultSet.getString("country_manufacturer");
        Manufacturer manufacturer = new Manufacturer(nameManufacturer, countryManufacturer);
        manufacturer.setId(manufacturerIdCar);
        Car car = new Car(modelCar, manufacturer);
        car.setId(idCar);
        car.setDrivers(getDriversForCar(idCar));
        return car;
    }

    private List<Driver> getDriversForCar(Long idCar) {
        String query = "SELECT drivers.name_driver, drivers.licence_number_driver, "
                + "cars_drivers.id_driver "
                + "FROM cars_drivers "
                + "JOIN drivers ON cars_drivers.id_driver = drivers.id_driver "
                + "WHERE cars_drivers.id_car = ?";
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, idCar);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name_driver");
                String licenceNumber = resultSet.getString("licence_number_driver");
                Long idDriver = resultSet.getObject("id_driver", long.class);
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
