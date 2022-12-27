package pl.mskreczko.restapi.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mskreczko.restapi.task.dto.TaskContentDto;
import pl.mskreczko.restapi.task.dto.TaskCreationDto;
import pl.mskreczko.restapi.user.User;
import pl.mskreczko.restapi.user.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskService taskService;

    @Test
    void findAllByUsername_returnsList() {
        List<Task> tasks = new ArrayList<>() {{
           add(new Task("walk the dog", "i need to walk my dog", null));
           add(new Task("buy groceries", "buy groceries", null));
        }};
        Mockito.when(taskRepository.findByUsername("test@test.com")).thenReturn(tasks);

        final var actual = taskService.findAllByUsername("test@test.com");

        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals("walk the dog", actual.get(0).title());
        Assertions.assertEquals(Status.ACTIVE, actual.get(0).status());
    }

    @Test
    void findAllByUsername_returnsEmptyList() {
        Mockito.when(taskRepository.findByUsername("test@test.com")).thenReturn(List.of());

        final var actual = taskService.findAllByUsername("test@test.com");

        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void findByIdAndUsername_returnsTask() {
        User user = new User();
        Task task = new Task("walk the dog", "i need to walk my dog", user);
        Mockito.when(taskRepository.findByIdAndUsername(1, "test@test.com")).thenReturn(task);

        TaskContentDto actual = taskService.findByIdAndUsername(1, "test@test.com").get();
        TaskContentDto expected = new TaskContentDto(task.getId(), task.getTitle(), task.getDescription(), task.getCreationDate(), task.getStatus());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findByIdAndUsername_returnsNull() {
        Mockito.when(taskRepository.findByIdAndUsername(1, "test@test.com")).thenReturn(null);

        Optional<TaskContentDto> actual = taskService.findByIdAndUsername(1, "test@test.com");

        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    void createTask_returnsTaskContentDto() {
        final var user = new User("test@test.com", "ZAQ!2wsx");
        final var dto = new TaskCreationDto("walk the dog", "i need to walk my dog");
        final var expected = new Task(dto.title(), dto.description(), user);

        Mockito.when(userService.loadUserByUsername("test@test.com")).thenReturn(user);
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(expected);

        final var actual = taskService.createNewTask("test@test.com", dto);

        Assertions.assertNull(actual.id());
        Assertions.assertEquals("walk the dog", actual.title());
        Assertions.assertEquals("i need to walk my dog", actual.description());
        Assertions.assertEquals(LocalDate.now(), actual.creationDate());
        Assertions.assertEquals(Status.ACTIVE, actual.status());
    }
}
