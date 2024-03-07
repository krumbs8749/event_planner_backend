package com.app.service;

import com.app.dto.AttendeeDto;
import com.app.dto.EventCompactDto;
import com.app.dto.EventDto;
import com.app.model.Attendee;
import com.app.model.Event;
import com.app.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final EventService eventService; // Handle event lookups

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository, EventService eventService) {
        this.attendeeRepository = attendeeRepository;
        this.eventService = eventService;
    }

    @Transactional
    public Attendee createAttendee(AttendeeDto attendeeDto) {
        Attendee attendee = convertToEntity(attendeeDto);
        attendeeRepository.save(attendee);
        assert attendee.getEventRegistered() != null;
        eventService.addAttendeeToEvent(attendee.getEventRegistered().getId(), attendee.getId());
        return attendee;
    }

    // Conversion from DTO to entity
    private Attendee convertToEntity(AttendeeDto dto) {
        Attendee attendee = new Attendee();
        attendee.setName(dto.getName());
        attendee.setEmail(dto.getEmail());
        attendee.setPhoneNumber(dto.getPhoneNumber());
        attendee.setStatus("pending");

        EventCompactDto eventDto = dto.getEventRegistered();
        if (eventDto != null) {
            Optional<Event> optionalEvent = eventService.getEventByNameAndLocationAndDateTime(
                    eventDto.getName(),
                    eventDto.getLocation(),
                    eventDto.getDateTime()
            );

            if (optionalEvent.isPresent()) {
                // Event exists, so we can set it to the attendee
                attendee.setEventRegistered(optionalEvent.get());
            } else {
                throw( new RuntimeException("Event not found"));
            }
        } else {
            attendee.setEventRegistered(null);
        }
        return attendee;
    }

    // Conversion from entity to DTO
    public AttendeeDto convertToDto(Attendee attendee) {
        AttendeeDto dto = new AttendeeDto();
        dto.setName(attendee.getName());
        dto.setEmail(attendee.getEmail());
        dto.setPhoneNumber(attendee.getPhoneNumber());
        // Assuming EventDto has a constructor or setters to initialize from an Event entity
        dto.setEventRegistered(new EventCompactDto(attendee.getEventRegistered().getName(), attendee.getEventRegistered().getLocation(), attendee.getEventRegistered().getDateTime())); // Simplified
        return dto;
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
