package com.app.repository;

import com.app.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    List<Attendee> findByNameAndEmail(String name, String email);
}