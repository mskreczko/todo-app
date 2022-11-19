package pl.mskreczko.restapi.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.restapi.user.dto.UserCreationDto;
import pl.mskreczko.restapi.user.dto.UserDto;
import pl.mskreczko.restapi.user.exceptions.UserAlreadyExistsException;
import pl.mskreczko.restapi.user.exceptions.UserDoesNotExistException;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Integer userId) {
        Optional<User> result = userService.getUserById(userId);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserDto user = new UserDto(result.get().getName(), result.get().getEmail(), result.get().getTasks());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/")
    public ResponseEntity<?> createUser(@RequestBody UserCreationDto userCreationDto) {
        User user = new User(userCreationDto.name(), userCreationDto.email(), userCreationDto.password());
        try {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Integer userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (UserDoesNotExistException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
