package pl.mskreczko.restapi.task.dto;

import pl.mskreczko.restapi.task.Status;

public record TaskPreviewDto(String title, Status status) {
}
