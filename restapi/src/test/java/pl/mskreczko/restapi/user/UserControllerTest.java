package pl.mskreczko.restapi.user;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.mskreczko.restapi.user.exceptions.UserAlreadyExistsException;
import pl.mskreczko.restapi.user.exceptions.UserDoesNotExistException;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    void getUserById_returnsNotFound() throws Exception {
        Mockito.when(userService.getUserById(1)).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                .get("/api/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUserById_returnsOk() throws Exception {
        User user = new User("John Doe", "jdoe@gmail.com", "ZAQ!2wsx");
        Mockito.when(userService.getUserById(1)).thenReturn(Optional.of(user));

        mvc.perform(MockMvcRequestBuilders
                .get("/api/users/1"))
                .andExpect(status().isOk());
    }

    // @Test
    // void createUser_returnsConflict() throws Exception {
    //     User user = new User("John Doe", "jdoe@gmail.com", "ZAQ!2wsx");
    //     UserService mock = mock(UserService.class);
    //     Mockito.doThrow(new UserAlreadyExistsException()).when(mock).createUser(user);

    //     mvc.perform(MockMvcRequestBuilders
    //             .post("/api/users")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content("{ \"name\": \"John Doe\", \"email\": \"jdoe@gmail.com\", \"password\": \"ZAQ!2wsx\" }"))
    //             .andExpect(status().isConflict());
    // }

    // @Test
    // void deleteUserById_returnsNotFound() throws Exception {
    //     UserService mock = mock(UserService.class);

    //     Mockito.doThrow(new UserDoesNotExistException()).when(mock).deleteUser(1);

    //     mvc.perform(MockMvcRequestBuilders
    //             .delete("/api/users/1"))
    //             .andExpect(status().isNotFound());
    // }
}
