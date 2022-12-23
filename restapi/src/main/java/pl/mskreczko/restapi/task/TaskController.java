package pl.mskreczko.restapi.task;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.restapi.task.dto.TaskContentDto;
import pl.mskreczko.restapi.task.dto.TaskCreationDto;

import java.net.URI;
import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"Authorization"})
public class TaskController {
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<?> getTasks() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok().body(taskService.findAllByUsername(username));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createTask(@RequestBody TaskCreationDto taskCreationDto) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        TaskContentDto task = taskService.createNewTask(username, taskCreationDto);
        return ResponseEntity.created(new URI("/api/v1/tasks/" + task.id())).body(task); // needs testing
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
