package pl.mskreczko.restapi.task;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.mskreczko.restapi.task.dto.TaskContentDto;
import pl.mskreczko.restapi.task.dto.TaskPreviewDto;
import pl.mskreczko.restapi.user.User;
import pl.mskreczko.restapi.user.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private UserService userService;

    @Test
    void getTasksByUserId_returnsNotFound() throws Exception {
        Mockito.when(userService.getUserById(1)).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                .get("/api/users/1/tasks"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTasksByUserId_returnsOk() throws Exception {
        User user = new User();
        List<TaskPreviewDto> tasks = new ArrayList<>() {{
            add(new TaskPreviewDto("task1", Status.ACTIVE));
            add(new TaskPreviewDto("task2", Status.DONE));
        }};
        Mockito.when(userService.getUserById(1)).thenReturn(Optional.of(user));
        Mockito.when(taskService.findAllByUserId(1)).thenReturn(tasks);

        mvc.perform(MockMvcRequestBuilders
                .get("/api/users/1/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    void getOneTaskByUserId_returnsNotFound() throws Exception {
        Mockito.when(userService.getUserById(1)).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                .get("/api/users/1/tasks/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getOneTaskByUserId_returnsOk() throws Exception {
        User user = new User();
        TaskContentDto task = new TaskContentDto("task1", "task", LocalDate.now(), Status.ACTIVE);

        Mockito.when(userService.getUserById(1)).thenReturn(Optional.of(user));
        Mockito.when(taskService.findOneTaskByUserId(1, 1)).thenReturn(Optional.of(task));

        mvc.perform(MockMvcRequestBuilders
                .get("/api/users/1/tasks/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createNewTask_returnsNotFound() throws Exception {
        Mockito.when(userService.getUserById(1)).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                .post("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"title\": \"task1\", \"description\": \"task\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createNewTask_returnsCreated() throws Exception{
        User user = new User();
        Mockito.when(userService.getUserById(1)).thenReturn(Optional.of(user));

        mvc.perform(MockMvcRequestBuilders
                .post("/api/users/1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"title\": \"task1\", \"description\": \"task\"}"))
                .andExpect(status().isCreated());
    }
}
