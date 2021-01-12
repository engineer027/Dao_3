package mate.academy.service;

import java.util.List;
import mate.academy.model.Manufacturer;

public interface ManufacturerService {
    Manufacturer create(Manufacturer manufacturer);

    Manufacturer get(Long id);

    Manufacturer update(Manufacturer manufacturer);

    boolean delete(Long id);

    List<Manufacturer> getAll();
}
