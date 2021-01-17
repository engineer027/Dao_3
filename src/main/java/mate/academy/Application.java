package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.model.Driver;
import mate.academy.model.Manufacturer;
import mate.academy.service.DriverService;
import mate.academy.service.ManufacturerService;

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
        driverService.create(driverKate);
        System.out.println("Test method create " + driverService.getAll());
    }
}
