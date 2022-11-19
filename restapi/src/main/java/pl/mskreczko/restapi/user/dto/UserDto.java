package pl.mskreczko.restapi.user.dto;

import pl.mskreczko.restapi.task.Task;

import java.util.List;

public record UserDto(String name, String email, List<Task> tasks) {
}
