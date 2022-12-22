package pl.mskreczko.restapi.task;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.restapi.task.dto.TaskCreationDto;
import pl.mskreczko.restapi.user.UserService;

import java.security.Principal;


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
}
