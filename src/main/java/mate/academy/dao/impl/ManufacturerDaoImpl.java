package mate.academy.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import mate.academy.dao.ManufacturerDao;
import mate.academy.db.Storage;
import mate.academy.model.Manufacturer;

public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        Storage.addManufactures(manufacturer);
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Storage.manufacturers.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        IntStream.range(0, Storage.manufacturers.size())
                .filter(i -> Storage.manufacturers.get(i).getId().equals(manufacturer.getId()))
                .forEach(i -> Storage.manufacturers.set(i, manufacturer));
        return manufacturer;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.manufacturers.removeIf(p -> p.getId().equals(id));
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }
}
