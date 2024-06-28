package com.github.sroigmas.taskschallenge.infrastructure.rest;

import com.github.sroigmas.taskschallenge.application.port.input.CreateTaskUseCase;
import com.github.sroigmas.taskschallenge.application.port.input.CreateTaskUseCase.CreateTaskCommand;
import com.github.sroigmas.taskschallenge.application.port.input.ListTasksUseCase;
import com.github.sroigmas.taskschallenge.domain.Task;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

  private final CreateTaskUseCase createTaskUseCase;
  private final ListTasksUseCase listTasksUseCase;

  public TaskController(CreateTaskUseCase createTaskUseCase, ListTasksUseCase listTasksUseCase) {
    this.createTaskUseCase = createTaskUseCase;
    this.listTasksUseCase = listTasksUseCase;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest) {
    CreateTaskCommand createTaskCommand =
        new CreateTaskCommand(
            taskRequest.userId(),
            taskRequest.title(),
            taskRequest.description(),
            taskRequest.dueDate(),
            taskRequest.tags());

    Task task = createTaskUseCase.createTask(createTaskCommand);

    return new TaskResponse(
        task.user().userId(),
        task.title(),
        task.description(),
        task.dueDate(),
        task.tags(),
        task.status());
  }

  @GetMapping
  public List<TaskResponse> listTasks(@RequestParam("user_id") UUID userId) {
    List<Task> tasks = listTasksUseCase.listTasks(userId);

    return tasks.stream()
        .map(
            task ->
                new TaskResponse(
                    task.user().userId(),
                    task.title(),
                    task.description(),
                    task.dueDate(),
                    task.tags(),
                    task.status()))
        .toList();
  }
}
