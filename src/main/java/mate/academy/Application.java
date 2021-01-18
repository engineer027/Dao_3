package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.model.Car;
import mate.academy.model.Driver;
import mate.academy.model.Manufacturer;
import mate.academy.service.CarService;
import mate.academy.service.DriverService;
import mate.academy.service.ManufacturerService;

import java.util.List;

public class Application {

    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {

        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);

        Manufacturer manufacturer = manufacturerService.get(1L);
        System.out.println("Test method get " + manufacturer.toString());

        manufacturer.setName("yyy");
        manufacturerService.update(manufacturer);
        System.out.println("Test method update " + manufacturer.toString());

        System.out.println("Test method getAll " + manufacturerService.getAll());

        Manufacturer manufacturer1 = new Manufacturer("sss", "ddd");
        manufacturerService.create(manufacturer1);
        System.out.println("Test method create " + manufacturerService.getAll());

        DriverService driverService = (DriverService) injector.getInstance(DriverService.class);

        Driver driver = driverService.get(1L);
        System.out.println("Test method get " + driver.toString());

        driver.setLicenceNumber("000");
        driverService.update(driver);
        System.out.println("Test method update " + driver.toString());

        System.out.println("Test method getAll " + driverService.getAll());

        Driver driverKate = new Driver("Kate", "696");
        driverService.create(driverKate); //
        System.out.println("Test method create " + driverService.getAll());

        CarService carService = (CarService) injector.getInstance(CarService.class);
        Car car = carService.get(1L);
        System.out.println("Test method get " + car.toString());

        Car carBMW = new Car("X5", manufacturer1);
        carService.create(carBMW); //
        System.out.println("Test method create " + carService.getAll());

        carBMW.setModel("X6");
        carService.update(carBMW);
        System.out.println("Test method update " + carService.getAll());

        carService.delete(2L);
        System.out.println("Test method delete " + carService.getAll());

        carService.addDriverToCar(driverKate, carBMW);
        System.out.println("Test method addDriverToCar " + carService.getAll());

        List<Car> allByDriver = carService.getAllByDriver(driverKate.getId());
        System.out.println("Test method getAllByDriver " + allByDriver.toString());

        carService.removeDriverFromCar(driverKate, carBMW);
        System.out.println("Test method removeDriverFromCar " + carService.getAll());


    }
}
