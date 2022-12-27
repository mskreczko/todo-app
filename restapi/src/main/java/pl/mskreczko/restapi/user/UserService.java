package pl.mskreczko.restapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mskreczko.restapi.user.exceptions.UserAlreadyExistsException;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerUser(String username, String password) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(username).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        userRepository.save(new User(username, bCryptPasswordEncoder.encode(password)));
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with such username"));
    }
}
