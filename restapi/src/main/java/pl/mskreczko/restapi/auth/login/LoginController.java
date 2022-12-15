package pl.mskreczko.restapi.auth.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.restapi.auth.login.dto.UserLoginDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody UserLoginDto credentials) {
        try {
            if (!loginService.authenticateUser(credentials.email(), credentials.password())) {
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(loginService.getJWTToken(credentials.email()));
    }
}
