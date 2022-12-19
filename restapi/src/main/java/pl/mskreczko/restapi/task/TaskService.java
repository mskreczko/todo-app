package pl.mskreczko.restapi.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mskreczko.restapi.task.dto.TaskCreationDto;
import pl.mskreczko.restapi.task.dto.TaskPreviewDto;
import pl.mskreczko.restapi.user.User;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public List<TaskPreviewDto> findAllByUsername(String username) {
        return taskRepository.findByUsername(username).stream().map(task -> new TaskPreviewDto(task.getId(), task.getTitle(), task.getStatus())).collect(Collectors.toList());
    }

    public void createNewTask(User user, TaskCreationDto taskCreationDto) {
        Task task = new Task(taskCreationDto.title(), taskCreationDto.description(), user);
        taskRepository.save(task);
    }
}
