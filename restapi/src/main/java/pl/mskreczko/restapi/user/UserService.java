package pl.mskreczko.restapi.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mskreczko.restapi.user.exceptions.UserAlreadyExistsException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Error"));
    }

    @Transactional
    public void setUserToken(String email, String token) {
        userRepository.setUserToken(email, token);
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void registerUser(User user) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    public boolean authenticateUser(String email, String password) {
        final var user = userRepository.findByEmail(email);
        if (user.isEmpty())
            return false;
        return bCryptPasswordEncoder.matches(password, user.get().getPassword());
    }
}
