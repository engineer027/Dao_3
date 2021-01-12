package mate.academy.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.model.Car;
import mate.academy.model.Driver;
import mate.academy.model.Manufacturer;

public class Storage {
    public static final List<Manufacturer> manufacturers = new ArrayList<>();
    public static final List<Driver> drivers = new ArrayList<>();
    public static final List<Car> cars = new ArrayList<>();
    private static Long manufacturersId = 0L;
    private static Long driversId = 0L;
    private static Long carsId = 0L;

    public static void addManufactures(Manufacturer manufacturer) {
        manufacturer.setId(++manufacturersId);
        manufacturers.add(manufacturer);
    }

    public static void addDriver(Driver driver) {
        driver.setId(++driversId);
        drivers.add(driver);
    }

    public static void addCar(Car car) {
        car.setId(++carsId);
        cars.add(car);
    }
}
