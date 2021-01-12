package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.model.Manufacturer;
import mate.academy.service.ManufacturerService;
import mate.academy.service.ManufacturerServiceImpl;

public class Application {

    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService) injector.getInstance(ManufacturerService.class);

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

        manufacturerFive.setCountry("BL");
        manufacturerService.update(manufacturerFive);
        System.out.println(manufacturerService.get(5L).toString());

        manufacturerService.delete(3L);
        System.out.println(manufacturerService.getAll());

    }
}
