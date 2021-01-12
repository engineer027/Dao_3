package mate.academy.dao.impl;

import mate.academy.dao.ManufacturerDao;
import mate.academy.db.Storage;
import mate.academy.lib.Dao;
import mate.academy.model.Manufacturer;
import java.util.List;
import java.util.Optional;

@Dao
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
        Optional<Manufacturer> first = Storage.manufacturers.stream()
                .filter(p -> p.getId().equals(manufacturer.getId()))
                .findFirst();
        first.get().setName(manufacturer.getName());
        first.get().setCountry(manufacturer.getCountry());
        return first.get();
    }

    @Override
    public boolean delete(Long id) {
        Optional<Manufacturer> first = Storage.manufacturers.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return Storage.manufacturers.remove(first.get());
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }
}
