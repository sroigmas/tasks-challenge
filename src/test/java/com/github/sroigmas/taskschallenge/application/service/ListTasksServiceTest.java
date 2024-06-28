package com.github.sroigmas.taskschallenge.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import com.github.sroigmas.taskschallenge.application.port.output.TaskRepository;
import com.github.sroigmas.taskschallenge.domain.Task;
import com.github.sroigmas.taskschallenge.domain.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListTasksServiceTest {

  @Mock private TaskRepository taskRepository;
  @InjectMocks private ListTasksService listTasksService;

  @Test
  void givenUserId_whenListingTasks_thenReturnsTasks() {
    UUID userId = UUID.randomUUID();
    Task task =
        new Task(
            new User(userId),
            "My task",
            "This is my task",
            LocalDateTime.of(2025, 6, 28, 12, 0),
            Optional.of(Set.of("tag1", "tag2")));

    when(taskRepository.findTasksByUserId(userId)).thenReturn(List.of(task));

    List<Task> tasks = listTasksService.listTasks(userId);

    assertFalse(tasks.isEmpty());
    assertEquals(1, tasks.size());
    assertEquals(task, tasks.getFirst());
  }
}
