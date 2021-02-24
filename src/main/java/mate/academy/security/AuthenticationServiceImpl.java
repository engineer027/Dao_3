package mate.academy.security;

import java.util.Optional;
import mate.academy.dao.DriverDao;
import mate.academy.exception.AuthenticationException;
import mate.academy.lib.Dao;
import mate.academy.lib.Inject;
import mate.academy.model.Driver;

@Dao
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driverFromDB = driverDao.findByLogin(login);
        if (driverFromDB.isPresent() && driverFromDB.get().getPassword().equals(password)) {
            return driverFromDB.get();
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
