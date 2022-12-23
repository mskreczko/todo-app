package pl.mskreczko.restapi.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mskreczko.restapi.task.dto.TaskContentDto;
import pl.mskreczko.restapi.task.dto.TaskCreationDto;
import pl.mskreczko.restapi.task.dto.TaskPreviewDto;
import pl.mskreczko.restapi.user.User;
import pl.mskreczko.restapi.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public List<TaskPreviewDto> findAllByUsername(String username) {
        return taskRepository.findByUsername(username).stream().map(task -> new TaskPreviewDto(task.getId(), task.getTitle(), task.getStatus())).collect(Collectors.toList());
    }

    public TaskContentDto createNewTask(String username, TaskCreationDto taskCreationDto) {
        User user = userService.loadUserByUsername(username);
        Task task = taskRepository.save(new Task(taskCreationDto.title(), taskCreationDto.description(), user));

        return new TaskContentDto(task.getId(), task.getTitle(), task.getDescription(), task.getCreationDate(), task.getStatus());
    }

    public void deleteTask(Integer taskId) {
        taskRepository.deleteById(taskId);
    }
}
