package pl.mskreczko.restapi.auth.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.restapi.user.dto.UserCreationDto;
import pl.mskreczko.restapi.user.exceptions.UserAlreadyExistsException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserCreationDto user) {
        try {
            registrationService.register(new UserCreationDto(user.email(), user.password()));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
