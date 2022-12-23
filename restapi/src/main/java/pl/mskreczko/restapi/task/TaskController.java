package pl.mskreczko.restapi.task;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.restapi.task.dto.TaskContentDto;
import pl.mskreczko.restapi.task.dto.TaskCreationDto;
import pl.mskreczko.restapi.task.dto.TaskPreviewDto;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"Authorization"})
public class TaskController {
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskPreviewDto>> getTasks() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok().body(taskService.findAllByUsername(username));
    }

    @PostMapping("/new")
    public ResponseEntity<TaskContentDto> createTask(@RequestBody TaskCreationDto taskCreationDto) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        TaskContentDto task = taskService.createNewTask(username, taskCreationDto);
        return ResponseEntity.created(new URI("/api/v1/tasks/" + task.id())).body(task);
    }

    @GetMapping
    public ResponseEntity<TaskContentDto> getTaskById(@RequestParam Integer id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<TaskContentDto> task = taskService.findByIdAndUsername(id, username);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTask(@RequestParam Optional<Integer> id) {
        if (id.isPresent()) {
            taskService.deleteTask(id.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
