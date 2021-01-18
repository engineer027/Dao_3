package mate.academy.dao;

import java.util.List;
import mate.academy.model.Car;
import mate.academy.model.Driver;

public interface CarDao extends GenericDao<Car, Long> {
    List<Car> getAllByDriver(Long driverId);

    void addDriverToCar(Driver driver, Car car);

    void removeDriverFromCar(Driver driver, Car car);

}
