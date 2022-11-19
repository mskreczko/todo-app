package pl.mskreczko.restapi.user;

import org.springframework.stereotype.Service;
import pl.mskreczko.restapi.user.exceptions.UserAlreadyExistsException;
import pl.mskreczko.restapi.user.exceptions.UserDoesNotExistException;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void createUser(User user) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        userRepository.save(user);
    }

    public void deleteUser(Integer id) throws UserDoesNotExistException {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserDoesNotExistException();
        }

        userRepository.deleteById(id);
    }
}
