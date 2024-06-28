package com.github.sroigmas.taskschallenge.application.port.input;

import com.github.sroigmas.taskschallenge.domain.Task;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CreateTaskUseCase {

  Task createTask(CreateTaskCommand createTaskCommand);

  record CreateTaskCommand(
      UUID userId,
      String title,
      String description,
      LocalDateTime dueDate,
      Optional<Set<String>> tags) {}
}
