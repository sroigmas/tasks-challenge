package com.github.sroigmas.taskschallenge.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

public record TaskRequest(
    @NotNull String title,
    @NotNull String description,
    @NotNull @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dueDate,
    Set<String> tags) {}
