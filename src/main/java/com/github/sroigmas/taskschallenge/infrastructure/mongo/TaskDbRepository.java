package com.github.sroigmas.taskschallenge.infrastructure.mongo;

import com.github.sroigmas.taskschallenge.application.port.output.TaskRepository;
import com.github.sroigmas.taskschallenge.domain.Task;
import com.github.sroigmas.taskschallenge.domain.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDbRepository implements TaskRepository {

  private final TaskMongoRepository taskMongoRepository;

  public TaskDbRepository(TaskMongoRepository taskMongoRepository) {
    this.taskMongoRepository = taskMongoRepository;
  }

  @Override
  public Task saveTask(Task task) {
    TaskDocument taskDocument =
        new TaskDocument(
            new UserDocument(task.user().userId().toString()),
            task.title(),
            task.description(),
            task.dueDate(),
            task.tags().orElse(null),
            task.status());

    TaskDocument savedTaskDocument = taskMongoRepository.save(taskDocument);

    return new Task(
        new User(UUID.fromString(savedTaskDocument.getUser().getUserId())),
        savedTaskDocument.getTitle(),
        savedTaskDocument.getDescription(),
        savedTaskDocument.getDueDate(),
        Optional.ofNullable(savedTaskDocument.getTags()),
        savedTaskDocument.getStatus());
  }

  @Override
  public List<Task> findTasksByUserId(UUID userId) {
    return taskMongoRepository.findByUserUserId(userId.toString()).stream()
        .map(
            taskDocument ->
                new Task(
                    new User(UUID.fromString(taskDocument.getUser().getUserId())),
                    taskDocument.getTitle(),
                    taskDocument.getDescription(),
                    taskDocument.getDueDate(),
                    Optional.ofNullable(taskDocument.getTags()),
                    taskDocument.getStatus()))
        .toList();
  }
}
