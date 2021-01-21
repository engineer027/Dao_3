package mate.academy.service;

import java.util.List;
import java.util.Optional;
import mate.academy.model.Driver;

public interface DriverService {
    Driver create(Driver driver);

    Driver get(Long id);

    List<Driver> getAll();

    Driver update(Driver driver);

    boolean delete(Long id);

    Optional<Driver> findByLogin(String login);
}
