package com.github.sroigmas.taskschallenge.domain;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public record Task(
    User user,
    String title,
    String description,
    LocalDateTime dueDate,
    Optional<Set<String>> tags,
    TaskStatus status) {

  public Task(
      User user,
      String title,
      String description,
      LocalDateTime dueDate,
      Optional<Set<String>> tags) {
    this(
        user,
        title,
        description,
        dueDate,
        tags.map(tagsSet -> tagsSet.stream().map(String::toLowerCase).collect(Collectors.toSet())),
        TaskStatus.PENDING);
  }
}
