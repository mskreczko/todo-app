package pl.mskreczko.restapi.task.dto;

import pl.mskreczko.restapi.task.Status;

public record TaskPreviewDto(Integer id, String title, Status status) {
}
