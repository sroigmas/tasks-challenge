package com.github.sroigmas.taskschallenge.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.sroigmas.taskschallenge.infrastructure.exception.InfrastructureValidationException;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TaskRequest(
    @NotNull UUID userId,
    @NotNull String title,
    @NotNull String description,
    @NotNull @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dueDate,
    Optional<Set<String>> tags) {

  public TaskRequest {
    if (dueDate.isBefore(LocalDateTime.now())) {
      throw new InfrastructureValidationException("due_date must be later than now.");
    }
  }
}
