package com.github.sroigmas.taskschallenge.application.service;

import com.github.sroigmas.taskschallenge.application.port.input.ListTasksUseCase;
import com.github.sroigmas.taskschallenge.application.port.output.TaskRepository;
import com.github.sroigmas.taskschallenge.domain.Task;
import java.util.List;
import java.util.UUID;

public class ListTasksService implements ListTasksUseCase {

  private final TaskRepository taskRepository;

  public ListTasksService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public List<Task> listTasks(UUID userId) {
    return taskRepository.findTasksByUserId(userId);
  }
}
