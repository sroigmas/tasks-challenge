package com.github.sroigmas.taskschallenge;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

public interface TestcontainersConfiguration {

  @Container
  MongoDBContainer mongoDbContainer = new MongoDBContainer(DockerImageName.parse("mongo:latest"));

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry properties) {
    properties.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
  }
}
