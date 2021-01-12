package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.model.Car;
import mate.academy.model.Driver;
import mate.academy.model.Manufacturer;
import mate.academy.service.CarService;
import mate.academy.service.DriverService;
import mate.academy.service.ManufacturerService;

public class Application {

    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        System.out.println("Test methods Manufacturer");

        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);

        Manufacturer manufacturerOne = new Manufacturer("ZAZ", "UA");
        Manufacturer manufacturerTwo = new Manufacturer("BMW", "DE");
        Manufacturer manufacturerThree = new Manufacturer("Skoda", "BL");
        Manufacturer manufacturerFour = new Manufacturer("Audi", "PL");
        Manufacturer manufacturerFive = new Manufacturer("LADA", "RU");

        manufacturerService.create(manufacturerOne);
        manufacturerService.create(manufacturerTwo);
        manufacturerService.create(manufacturerThree);
        manufacturerService.create(manufacturerFour);
        manufacturerService.create(manufacturerFive);
        System.out.println("Test method create " + manufacturerService.getAll());

        manufacturerFive.setCountry("BL");
        manufacturerService.update(manufacturerFive);
        System.out.println("Test method update " + manufacturerService.get(5L).toString());

        manufacturerService.delete(3L);
        System.out.println("Test method delete " + manufacturerService.getAll());

        System.out.println("Test methods Car");

        DriverService driverService = (DriverService) injector
                .getInstance(DriverService.class);

        Driver driverOne = new Driver("Bob", "234");
        Driver driverTwo = new Driver("Alice", "235");
        Driver driverThree = new Driver("Jon", "236");
        Driver driverFour = new Driver("Kate", "237");

        driverService.create(driverOne);
        driverService.create(driverTwo);
        driverService.create(driverThree);
        driverService.create(driverFour);
        System.out.println("Test method create " + driverService.getAll());

        driverOne.setLicenceNumber("000");
        driverService.update(driverOne);
        System.out.println("Test method update " + driverService.get(1L).toString());

        driverService.delete(2L);
        System.out.println("Test method delete " + driverService.getAll());

        System.out.println("Test methods Driver");

        Car carOne = new Car("ZAZ", manufacturerOne);
        Car carTwo = new Car("BMW", manufacturerTwo);
        Car carThree = new Car("Skoda", manufacturerThree);

        carOne.setDrivers(driverOne);
        carTwo.setDrivers(driverTwo);
        carThree.setDrivers(driverThree);

        CarService carService = (CarService) injector.getInstance(CarService.class);

        carService.create(carOne);
        carService.create(carTwo);
        carService.create(carThree);
        System.out.println("Test method create " + carService.getAll());

        carOne.setModel("LADA");
        carService.update(carOne);
        System.out.println("Test method update " + carService.get(1L).toString());

        carService.delete(2L);
        System.out.println("Test method delete " + carService.getAll());

        carService.addDriverToCar(driverFour, carOne);
        System.out.println("Test method addDriverToCar " + carService.get(1L).getDrivers());

        System.out.println("Test method getAllByDriver " + carService.getAllByDriver(1L));

    }
}
