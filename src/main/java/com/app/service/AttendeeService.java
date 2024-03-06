package com.app.service;

import com.app.model.Attendee;
import com.app.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Transactional
    public Attendee createOrUpdateAttendee(Attendee attendee) {
        // Add logic if needed
        return attendeeRepository.save(attendee);
    }

    public Optional<Attendee> findById(Long id) {
        return attendeeRepository.findById(id);
    }

    public List<Attendee> findAll() {
        return attendeeRepository.findAll();
    }

    @Transactional
    public void deleteAttendee(Long id) {
        attendeeRepository.deleteById(id);
    }
}
