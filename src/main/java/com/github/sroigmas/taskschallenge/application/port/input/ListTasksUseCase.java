package com.github.sroigmas.taskschallenge.application.port.input;

import com.github.sroigmas.taskschallenge.domain.Task;
import java.util.List;
import java.util.UUID;

public interface ListTasksUseCase {

  List<Task> listTasks(UUID userId);
}
