package pl.mskreczko.restapi.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mskreczko.restapi.user.User;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void findOneTaskByUserId_returnsEmpty() {
        Mockito.when(taskRepository.findByUserIdAndTaskId(1, 1)).thenReturn(null);

        final var actual = taskService.findOneTaskByUserId(1, 1);

        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    void findOneTaskByUserId_returnsTaskContentDto() {
        User user = new User();
        Task task = new Task("task1", "task", user);
        Mockito.when(taskRepository.findByUserIdAndTaskId(1, 1)).thenReturn(task);

        final var actual = taskService.findOneTaskByUserId(1, 1);

        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals("task1", actual.get().title());
        Assertions.assertEquals(Status.ACTIVE, actual.get().status());
    }
}
