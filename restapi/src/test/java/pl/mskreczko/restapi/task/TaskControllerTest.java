package pl.mskreczko.restapi.task;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.mskreczko.restapi.task.dto.TaskContentDto;
import pl.mskreczko.restapi.task.dto.TaskPreviewDto;

import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TaskController.class)
public class TaskControllerTest {

    @MockBean
    private TaskService taskService;

    @Autowired
    MockMvc mvc;

    @Test
    void getTasks_returnsOk() throws Exception {
        Mockito.when(taskService.findAllByUsername("test@test.com")).thenReturn(
                List.of(new TaskPreviewDto(1, "walk the dog", Status.ACTIVE)));

        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/tasks").with(user("test@test.com").roles("USER")))
                .andExpect(status().isOk());
    }

    // @Test
    // void createTasks_returnsCreated() throws Exception {
    //     TaskCreationDto taskCreationDto = new TaskCreationDto("walk the dog", "i need to walk my dog");
    //     Task task = new Task("walk the dog", "i need to walk my dog", null);
    //     TaskContentDto taskContentDto = new TaskContentDto(task.getId(), task.getTitle(), task.getDescription(), task.getCreationDate(), task.getStatus());
    //     Mockito.when(taskService.createNewTask("test@test.com", taskCreationDto)).thenReturn(taskContentDto);

    //     mvc.perform(MockMvcRequestBuilders
    //             .post("/api/v1/tasks/new").with(user("test@test.com").roles("USER"))
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content("{\"title\": \"walk the dog\", \"description\": \"i need to walk my dog\"}"))
    //             .andExpect(status().isCreated());
    // }

    @Test
    void getTaskById_returnsOk() throws Exception {
        Task task = new Task("walk the dog", "i need to walk my dog", null);
        Mockito.when(taskService.findByIdAndUsername(1, "test@test.com")).thenReturn(
                Optional.of(new TaskContentDto(task.getId(), task.getTitle(), task.getDescription(), task.getCreationDate(), task.getStatus()))
        );

        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/tasks/1").with(user("test@test.com").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    void getTaskById_returnsNotFound() throws Exception {
        Mockito.when(taskService.findByIdAndUsername(1, "test@test.com")).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/tasks/1").with(user("test@test.com").roles("USER")))
                .andExpect(status().isNotFound());
    }
}
