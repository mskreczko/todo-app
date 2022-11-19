package pl.mskreczko.restapi.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mskreczko.restapi.user.exceptions.UserAlreadyExistsException;
import pl.mskreczko.restapi.user.exceptions.UserDoesNotExistException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserById_returnsEmpty() {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());
        final var given = userService.getUserById(1);

        Assertions.assertTrue(given.isEmpty());
    }

    @Test
    void getUserById_returnsUser() {
        User user = new User("John Doe", "jdoe@gmail.com", "ZAQ!2wsx");
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        final var given = userService.getUserById(1);

        Assertions.assertTrue(given.isPresent());
    }

    @Test
    void createUser_throwsException() {
        User user = new User("John Doe", "jdoe@gmail.com", "ZAQ!2wsx");
        Mockito.when(userRepository.findByEmail("jdoe@gmail.com")).thenThrow(new UserAlreadyExistsException());

        try {
            userService.createUser(user);
        } catch (UserAlreadyExistsException e) {
            Assertions.assertInstanceOf(UserAlreadyExistsException.class, e);
        }
    }

    @Test
    void deleteUser_throwsException() {
        Mockito.when(userRepository.findById(1)).thenThrow(new UserDoesNotExistException());

        try {
            userService.deleteUser(1);
        } catch (UserDoesNotExistException e) {
            Assertions.assertInstanceOf(UserDoesNotExistException.class, e);
        }
    }
}
