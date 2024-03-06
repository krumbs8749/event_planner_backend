package com.app.repository;

import com.app.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository <Event, Long> {

    Optional<Event> findByNameAndLocationAndDateTime(String name, String location, Date dateTime);
}
