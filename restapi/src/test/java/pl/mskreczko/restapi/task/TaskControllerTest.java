package pl.mskreczko.restapi.task;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.mskreczko.restapi.task.dto.TaskContentDto;
import pl.mskreczko.restapi.task.dto.TaskCreationDto;
import pl.mskreczko.restapi.task.dto.TaskPreviewDto;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TaskController.class)
public class TaskControllerTest {

    @MockBean
    private TaskService taskService;

    @Autowired
    MockMvc mvc;

    @Test
    @WithMockUser
    void getTasks_returnsOk() throws Exception {
        Mockito.when(taskService.findAllByUsername("test@test.com")).thenReturn(
                List.of(new TaskPreviewDto(1, "walk the dog", Status.ACTIVE)));

        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void createTasks_returnsCreated() throws Exception {
        // TaskCreationDto taskCreationDto = new TaskCreationDto("walk the dog", "i need to walk my dog");
        // Task task = new Task("walk the dog", "i need to walk my dog", null);
        // TaskContentDto taskContentDto = new TaskContentDto(task.getId(), task.getTitle(), task.getDescription(), task.getCreationDate(), task.getStatus());
        // Mockito.when(taskService.createNewTask("test@test.com", taskCreationDto)).thenReturn(taskContentDto);

        // mvc.perform(MockMvcRequestBuilders
        //         .post("/api/v1/tasks/new")
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .content("{\"title\": \"walk the dog\", \"description\":  \"i need to walk my doog\"}"))
        //         .andExpect(status().isCreated());
    }

    @Test
    void getTaskById_returnsOk() {

    }

    @Test
    void getTaskById_returnsNotFound() {

    }

    @Test
    void deleteTask_returnsOk() {

    }

    @Test
    void deleteTask_returnsBadRequest() {

    }
}
