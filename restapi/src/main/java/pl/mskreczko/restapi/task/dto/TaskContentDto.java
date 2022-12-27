package pl.mskreczko.restapi.task.dto;

import pl.mskreczko.restapi.task.Status;

import java.time.LocalDate;

public record TaskContentDto(Integer id, String title, String description, LocalDate creationDate, Status status) {
}
