package pl.mskreczko.restapi.auth.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mskreczko.restapi.config.JwtAccessTokenProvider;
import pl.mskreczko.restapi.user.User;
import pl.mskreczko.restapi.user.UserService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserService userService;
    private final JwtAccessTokenProvider jwtAccessTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean authenticateUser(String username, String password) throws UsernameNotFoundException {
        try {
            User user = userService.loadUserByUsername(username);
            if (bCryptPasswordEncoder.matches(password, user.getPassword()))
                return true;
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("No user with such username");
        }

        return false;
    }

    public String getJWTToken(String username) {
        User user = userService.loadUserByUsername(username);
        return jwtAccessTokenProvider.getAccessToken(user.getUsername(), List.of("USER"));
    }
}
