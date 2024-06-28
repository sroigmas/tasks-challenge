package com.github.sroigmas.taskschallenge.application.port.output;

import com.github.sroigmas.taskschallenge.domain.Task;
import java.util.List;
import java.util.UUID;

public interface TaskRepository {

  Task saveTask(Task task);

  List<Task> findTasksByUserId(UUID userId);
}
