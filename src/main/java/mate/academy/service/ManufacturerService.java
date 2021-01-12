package mate.academy.service;

import mate.academy.model.Manufacturer;
import java.util.List;

public interface ManufacturerService {
    Manufacturer create(Manufacturer manufacturer);

    Manufacturer get(Long id);

    Manufacturer update(Manufacturer manufacturer);

    boolean delete(Long id);

    List<Manufacturer> getAll();
}
