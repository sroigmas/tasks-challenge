package com.github.sroigmas.taskschallenge.infrastructure.configuration;

import com.github.sroigmas.taskschallenge.application.port.input.CreateTaskUseCase;
import com.github.sroigmas.taskschallenge.application.port.input.ListTasksUseCase;
import com.github.sroigmas.taskschallenge.application.port.output.TaskRepository;
import com.github.sroigmas.taskschallenge.application.service.CreateTaskService;
import com.github.sroigmas.taskschallenge.application.service.ListTasksService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfiguration {

  @Bean
  public CreateTaskUseCase createTaskUseCase(TaskRepository taskRepository) {
    return new CreateTaskService(taskRepository);
  }

  @Bean
  public ListTasksUseCase listTasksUseCase(TaskRepository taskRepository) {
    return new ListTasksService(taskRepository);
  }
}
