package pl.mskreczko.restapi.auth.registration;

import org.springframework.stereotype.Service;
import pl.mskreczko.restapi.user.User;
import pl.mskreczko.restapi.user.UserService;
import pl.mskreczko.restapi.user.dto.UserCreationDto;

@Service
public class RegistrationService {

    private final UserService userService;

    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public void register(UserCreationDto userDto) {
        userService.registerUser(new User(userDto.email(), userDto.password()));
    }
}
