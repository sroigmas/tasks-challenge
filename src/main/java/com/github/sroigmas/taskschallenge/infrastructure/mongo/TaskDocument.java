package com.github.sroigmas.taskschallenge.infrastructure.mongo;

import com.github.sroigmas.taskschallenge.domain.TaskStatus;
import java.time.LocalDateTime;
import java.util.Set;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TaskDocument {

  @Indexed private String userId;

  private String title;

  private String description;

  private LocalDateTime dueDate;

  private Set<String> tags;

  private TaskStatus status;

  public TaskDocument() {}

  public TaskDocument(
      String userId,
      String title,
      String description,
      LocalDateTime dueDate,
      Set<String> tags,
      TaskStatus status) {
    this.userId = userId;
    this.title = title;
    this.description = description;
    this.dueDate = dueDate;
    this.tags = tags;
    this.status = status;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }

  public Set<String> getTags() {
    return tags;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }
}
