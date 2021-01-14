package mate.academy.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.dao.ManufacturerDao;
import mate.academy.lib.Dao;
import mate.academy.model.Manufacturer;
import mate.academy.util.ConnectionUtil;

@Dao
public class ManufacturerDaoJdbc implements ManufacturerDao {

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        String query = "INSERT INTO manufacturer (nameManufacturer, countryManufacturer) "
                + "VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Could not create manufacturer", e);
        }
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {

        String query = "SELECT * FROM manufacturer where idManufacturer = ?"
                + " AND deleteManufacturer = FALSE";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long idManufacturer = resultSet.getLong("idManufacturer");
                String nameManufacturer = resultSet.getString("nameManufacturer");
                String countryManufacturer = resultSet
                        .getString("countryManufacturer");
                Manufacturer manufacturer = new Manufacturer(nameManufacturer,
                        countryManufacturer);
                manufacturer.setId(idManufacturer);
                statement.close();
                return Optional.of(manufacturer);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not get manufacturer", e);
        }
        return Optional.empty();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE manufacturer SET nameManufacturer = ?, "
                + "countryManufacturer = ? WHERE idManufacturer = ? "
                + "AND deleteManufacturer = FALSE";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.setLong(3, manufacturer.getId());
            statement.executeUpdate();
            statement.close();
            return manufacturer;
        } catch (SQLException e) {
            throw new RuntimeException("Could update manufacturer", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE manufacturer SET deleteManufacturer = ? "
                + "WHERE idManufacturer = ? ";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setBoolean(1, true);
            statement.setLong(2, id);
            int delete = statement.executeUpdate();
            statement.close();
            return delete != 0;
        } catch (SQLException e) {
            throw new RuntimeException("Could not delete manufacturer", e);
        }
    }

    @Override
    public List<Manufacturer> getAll() {
        String query = "SELECT * FROM manufacturer where deleteManufacturer = FALSE";
        List<Manufacturer> manufacturers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long idManufacturer = resultSet.getLong("idManufacturer");
                String nameManufacturer = resultSet.getString("nameManufacturer");
                String countryManufacturer = resultSet.getString("countryManufacturer");
                Manufacturer manufacturer = new Manufacturer(nameManufacturer, countryManufacturer);
                manufacturer.setId(idManufacturer);
                manufacturers.add(manufacturer);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all manufacturers", e);
        }
        return manufacturers;
    }
}
