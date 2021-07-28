package taxi.service.impl;

import taxi.exception.AuthenticationException;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Driver;
import taxi.service.AuthenticationService;
import taxi.service.DriverService;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String username, String password) throws AuthenticationException {
        Optional<Driver> user = driverService.findByLogin(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        throw new AuthenticationException("Username or password was incorrect");
    }
}
