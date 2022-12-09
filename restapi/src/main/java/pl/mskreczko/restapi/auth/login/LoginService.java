package pl.mskreczko.restapi.auth.login;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mskreczko.restapi.user.User;
import pl.mskreczko.restapi.user.UserService;

import java.util.Optional;

@Service
public class LoginService {

    private final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }

    public boolean authenticateUser(String email, String password) throws UsernameNotFoundException  {
        return userService.authenticateUser(email, password);
    }

    public void saveToken(String email, String token) {
        userService.setUserToken(email, token);
    }
}
