package mate.academy.security;

import mate.academy.exception.AuthenticationException;
import mate.academy.lib.Dao;
import mate.academy.lib.Inject;
import mate.academy.model.Driver;
import mate.academy.service.DriverService;

@Dao
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Driver driverFromDB = driverService.findByLogin(login)
                .orElseThrow(() -> new AuthenticationException("Incorrect login or password"));

        if (driverFromDB.getPassword().equals(password)) {
            return driverFromDB;
        }
        throw new AuthenticationException("Incorrect login or password");
    }
}
