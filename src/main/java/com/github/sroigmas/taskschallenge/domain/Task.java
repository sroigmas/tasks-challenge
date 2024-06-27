package com.github.sroigmas.taskschallenge.domain;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record Task(
    String title, String description, LocalDateTime dueDate, Set<String> tags, TaskStatus status) {

  public Task(String title, String description, LocalDateTime dueDate, Set<String> tags) {
    this(
        title,
        description,
        dueDate,
        tags.stream().map(String::toLowerCase).collect(Collectors.toSet()),
        TaskStatus.PENDING);
  }
}
