package com.github.sroigmas.taskschallenge.infrastructure.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest) {
    return null;
  }
}
