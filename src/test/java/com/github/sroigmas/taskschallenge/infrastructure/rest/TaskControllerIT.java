package com.github.sroigmas.taskschallenge.infrastructure.rest;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.sroigmas.taskschallenge.TestcontainersConfiguration;
import com.github.sroigmas.taskschallenge.domain.TaskStatus;
import com.github.sroigmas.taskschallenge.infrastructure.mongo.TaskDocument;
import com.github.sroigmas.taskschallenge.infrastructure.mongo.TaskMongoRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ImportTestcontainers(TestcontainersConfiguration.class)
@AutoConfigureMockMvc
class TaskControllerIT {

  @Autowired private MockMvc mvc;

  private final ObjectMapper objectMapper =
      new ObjectMapper().registerModule(new JavaTimeModule()).registerModule(new Jdk8Module());

  @Autowired private TaskMongoRepository taskMongoRepository;

  @Test
  void givenTask_whenCreatingTask_thenTaskIsCreated() throws Exception {
    TaskRequest taskRequest =
        new TaskRequest(
            UUID.randomUUID(),
            "My task",
            "This is my task",
            LocalDateTime.of(2025, 6, 28, 12, 0),
            Optional.of(Set.of("tag1", "tag2")));

    ResultActions result =
        mvc.perform(
                post("/api/v1/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(taskRequest)))
            .andDo(print());

    result
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.user_id").value(taskRequest.userId().toString()))
        .andExpect(jsonPath("$.title").value(taskRequest.title()))
        .andExpect(jsonPath("$.description").value(taskRequest.description()))
        .andExpect(
            jsonPath("$.due_date")
                .value(
                    taskRequest
                        .dueDate()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))))
        .andExpect(jsonPath("$.tags", hasSize(taskRequest.tags().get().size())))
        .andExpect(jsonPath("$.tags", hasItem(taskRequest.tags().get().stream().toList().get(0))))
        .andExpect(jsonPath("$.tags", hasItem(taskRequest.tags().get().stream().toList().get(1))))
        .andExpect(jsonPath("$.status").value(TaskStatus.PENDING.name()));

    List<TaskDocument> tasks =
        taskMongoRepository.findByUserUserId(taskRequest.userId().toString());

    assertFalse(tasks.isEmpty());
    assertEquals(taskRequest.userId().toString(), tasks.getFirst().getUser().getUserId());
    assertEquals(taskRequest.title(), tasks.getFirst().getTitle());
    assertEquals(taskRequest.description(), tasks.getFirst().getDescription());
    assertEquals(taskRequest.dueDate(), tasks.getFirst().getDueDate());
    assertEquals(taskRequest.tags().get(), tasks.getFirst().getTags());
    assertEquals(TaskStatus.PENDING, tasks.getFirst().getStatus());
  }
}
