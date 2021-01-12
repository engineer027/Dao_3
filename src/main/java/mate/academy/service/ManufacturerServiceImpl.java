package mate.academy.service;

import mate.academy.dao.ManufacturerDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Manufacturer;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Inject
    private ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return manufacturerDao.create(manufacturer);
    }

    @Override
    public Manufacturer get(Long id) {
        return manufacturerDao.get(id).get();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return manufacturerDao.update(manufacturer);
    }

    @Override
    public boolean delete(Long id) {
        return manufacturerDao.delete(id);
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerDao.getAll();
    }
}
