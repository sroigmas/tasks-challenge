package com.github.sroigmas.taskschallenge.application.service;

import com.github.sroigmas.taskschallenge.application.port.input.CreateTaskUseCase;
import com.github.sroigmas.taskschallenge.application.port.output.TaskRepository;
import com.github.sroigmas.taskschallenge.domain.Task;
import com.github.sroigmas.taskschallenge.domain.User;

public class CreateTaskService implements CreateTaskUseCase {

  private final TaskRepository taskRepository;

  public CreateTaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public Task createTask(CreateTaskCommand createTaskCommand) {
    Task task =
        new Task(
            new User(createTaskCommand.userId()),
            createTaskCommand.title(),
            createTaskCommand.description(),
            createTaskCommand.dueDate(),
            createTaskCommand.tags());

    return taskRepository.saveTask(task);
  }
}
