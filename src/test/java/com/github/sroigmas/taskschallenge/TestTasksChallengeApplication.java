package com.github.sroigmas.taskschallenge;

import org.springframework.boot.SpringApplication;

public class TestTasksChallengeApplication {

  public static void main(String[] args) {
    SpringApplication.from(TasksChallengeApplication::main).with(TestcontainersConfiguration.class)
        .run(args);
  }

}
