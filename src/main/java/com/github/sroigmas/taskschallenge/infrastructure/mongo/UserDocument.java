package com.github.sroigmas.taskschallenge.infrastructure.mongo;

import org.springframework.data.mongodb.core.index.Indexed;

public class UserDocument {

  @Indexed private String userId;

  public UserDocument() {}

  public UserDocument(String userId) {
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
