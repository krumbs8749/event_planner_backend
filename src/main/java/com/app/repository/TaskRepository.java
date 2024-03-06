package com.app.repository;
import com.app.model.Event;
import com.app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {


        Optional<Task> findByNameAndEvent(String name, Event event);
}
