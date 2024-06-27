package com.github.sroigmas.taskschallenge.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.sroigmas.taskschallenge.domain.TaskStatus;
import java.time.LocalDateTime;
import java.util.Set;

public record TaskResponse(
    String title,
    String description,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dueDate,
    Set<String> tags,
    TaskStatus status) {}
