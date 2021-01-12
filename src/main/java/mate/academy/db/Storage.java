package mate.academy.db;

import mate.academy.model.Car;
import mate.academy.model.Manufacturer;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Long manufacturersId = 0L;
    public static final List<Manufacturer> manufacturers = new ArrayList<>();

    public static void addManufactures(Manufacturer manufacturer) {
        manufacturersId++;
        manufacturer.setId(manufacturersId);
        manufacturers.add(manufacturer);
    }
}
