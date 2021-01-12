package mate.academy.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.model.Manufacturer;

public class Storage {
    public static final List<Manufacturer> manufacturers = new ArrayList<>();
    private static Long manufacturersId = 0L;

    public static void addManufactures(Manufacturer manufacturer) {
        manufacturer.setId(++manufacturersId);
        manufacturers.add(manufacturer);
    }
}
