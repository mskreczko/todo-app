package pl.mskreczko.restapi.task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.restapi.task.dto.TaskCreationDto;
import pl.mskreczko.restapi.user.UserService;


@RestController
@RequestMapping("/api")
public class TaskController {
    private TaskService taskService;
    private UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/tasks")
    public ResponseEntity<?> getTasksByUserId(@PathVariable("userId") Integer userId) {
        if (userService.getUserById(userId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskService.findAllByUserId(userId));
    }

    @GetMapping("/users/{userId}/tasks/{taskId}")
    public ResponseEntity<?> getOneTaskByUserId(@PathVariable("userId") Integer userId, @PathVariable("taskId") Integer taskId) {
        if (userService.getUserById(userId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(taskService.findOneTaskByUserId(userId, taskId));
    }

    @PostMapping("/users/{userId}/tasks")
    public ResponseEntity<?> createNewTask(@PathVariable("userId") Integer userId, @RequestBody TaskCreationDto taskCreationDto) {
        if (userService.getUserById(userId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        taskService.createNewTask(userService.getUserById(userId).get(), taskCreationDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // @DeleteMapping("/users/{userId}/tasks/{taskId}")
    // public ResponseEntity<?> deleteTaskById(@PathVariable("userId") Integer userId, @PathVariable("taskId") Integer taskId) {

    // }
}
