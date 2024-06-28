package com.github.sroigmas.taskschallenge.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.sroigmas.taskschallenge.domain.TaskStatus;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public record TaskResponse(
    UUID userId,
    String title,
    String description,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dueDate,
    Optional<Set<String>> tags,
    TaskStatus status) {}
