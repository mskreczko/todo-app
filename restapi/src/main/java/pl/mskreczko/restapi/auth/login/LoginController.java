package pl.mskreczko.restapi.auth.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.restapi.auth.login.dto.UserLoginDto;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody UserLoginDto credentials) {
        try {
            if (!loginService.authenticateUser(credentials.email(), credentials.password())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String token = UUID.randomUUID().toString();
        loginService.saveToken(credentials.email(), token);

        return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
    }
}
