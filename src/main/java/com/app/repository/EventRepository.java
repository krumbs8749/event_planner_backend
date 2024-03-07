package com.app.repository;

import com.app.model.Event;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EventRepository extends JpaRepository <Event, Long> {

    Optional<Event> findByNameAndLocationAndDateTime(String name, String location, LocalDateTime dateTime);

    void deleteByNameAndLocationAndDateTime(@NotBlank(message = "Name cannot be blank") String name, String location, LocalDateTime dateTime);
}
