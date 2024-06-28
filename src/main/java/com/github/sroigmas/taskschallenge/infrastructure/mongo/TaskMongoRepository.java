package com.github.sroigmas.taskschallenge.infrastructure.mongo;

import java.util.List;
import org.springframework.data.repository.ListCrudRepository;

public interface TaskMongoRepository extends ListCrudRepository<TaskDocument, String> {

  List<TaskDocument> findByUserUserId(String userId);
}
