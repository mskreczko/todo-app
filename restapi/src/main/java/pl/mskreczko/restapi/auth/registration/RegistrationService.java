package pl.mskreczko.restapi.auth.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mskreczko.restapi.user.UserService;
import pl.mskreczko.restapi.user.dto.UserCreationDto;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final UserService userService;

    public void register(UserCreationDto userDto) {
        userService.registerUser(userDto.email(), userDto.password());
    }
}
