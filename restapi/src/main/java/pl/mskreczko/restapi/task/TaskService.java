package pl.mskreczko.restapi.task;

import org.springframework.stereotype.Service;
import pl.mskreczko.restapi.task.dto.TaskContentDto;
import pl.mskreczko.restapi.task.dto.TaskCreationDto;
import pl.mskreczko.restapi.task.dto.TaskPreviewDto;
import pl.mskreczko.restapi.user.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskPreviewDto> findAllByUserId(Integer userId) {
        return taskRepository.findByUserId(userId).stream().map(task -> new TaskPreviewDto(task.getId(), task.getTitle(), task.getStatus())).collect(Collectors.toList());
    }

    public Optional<TaskContentDto> findOneTaskByUserId(Integer userId, Integer taskId) {
        Optional<Task> task = Optional.ofNullable(taskRepository.findByUserIdAndTaskId(userId, taskId));
        if (task.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new TaskContentDto(task.get().getTitle(), task.get().getDescription(), task.get().getCreationDate(), task.get().getStatus()));
    }

    public void createNewTask(User user, TaskCreationDto taskCreationDto) {
        Task task = new Task(taskCreationDto.title(), taskCreationDto.description(), user);
        taskRepository.save(task);
    }
}
