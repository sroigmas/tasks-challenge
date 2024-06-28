package com.github.sroigmas.taskschallenge.application.service;

import static org.mockito.Mockito.verify;

import com.github.sroigmas.taskschallenge.application.port.input.CreateTaskUseCase.CreateTaskCommand;
import com.github.sroigmas.taskschallenge.application.port.output.TaskRepository;
import com.github.sroigmas.taskschallenge.domain.Task;
import com.github.sroigmas.taskschallenge.domain.User;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateTaskServiceTest {

  @Mock private TaskRepository taskRepository;
  @InjectMocks private CreateTaskService createTaskService;

  @Test
  void givenTask_whenCreatingTask_thenTaskIsCreated() {
    UUID userId = UUID.randomUUID();
    String title = "My task";
    String description = "This is my task";
    LocalDateTime dueDate = LocalDateTime.of(2025, 6, 28, 12, 0);
    Optional<Set<String>> tags = Optional.of(Set.of("tag1", "tag2"));

    CreateTaskCommand createTaskCommand =
        new CreateTaskCommand(userId, title, description, dueDate, tags);

    Task task = new Task(new User(userId), title, description, dueDate, tags);

    createTaskService.createTask(createTaskCommand);

    verify(taskRepository).saveTask(task);
  }
}
