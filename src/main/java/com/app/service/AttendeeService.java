package com.app.service;

import com.app.dto.AttendeeDto;
import com.app.dto.EventCompactDto;
import com.app.model.Attendee;
import com.app.model.Event;
import com.app.repository.AttendeeRepository;
import com.app.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final EventRepository eventRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository, EventRepository eventRepository) {
        this.attendeeRepository = attendeeRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public Attendee createAttendee(AttendeeDto attendeeDto) {
        Attendee attendee = convertToEntity(attendeeDto);

        EventCompactDto eventDto = attendeeDto.getEventRegistered();
        if (eventDto != null) {
            Optional<Event> optionalEvent = eventRepository.findByNameAndLocationAndDateTime(
                    eventDto.getName(),
                    eventDto.getLocation(),
                    eventDto.getDateTime()
            );

            if (optionalEvent.isPresent()) {
                Event event = optionalEvent.get();
                attendee.setEvent(event); // Associate event with attendee
                event.getAttendees().add(attendee); // Add attendee to event's list
                attendeeRepository.save(attendee);
            } else {
                throw new RuntimeException("Event not found");
            }
        } else {
            attendeeRepository.save(attendee);
        }

        return attendee;
    }

    // Conversion from DTO to entity
    private Attendee convertToEntity(AttendeeDto dto) {
        Attendee attendee = new Attendee();
        attendee.setId(dto.getId());
        attendee.setName(dto.getName());
        attendee.setEmail(dto.getEmail());
        attendee.setPhoneNumber(dto.getPhoneNumber());
        attendee.setStatus(dto.getStatus());
        attendee.setRegistrationDate(dto.getRegistrationDate());
        attendee.setTicketType(dto.getTicketType());
        attendee.setCompany(dto.getCompany());
        attendee.setNotes(dto.getNotes());
        return attendee;
    }

    // Conversion from entity to DTO
    public AttendeeDto convertToDto(Attendee attendee) {
        AttendeeDto dto = new AttendeeDto();
        dto.setId(attendee.getId());
        dto.setName(attendee.getName());
        dto.setEmail(attendee.getEmail());
        dto.setPhoneNumber(attendee.getPhoneNumber());
        dto.setStatus(attendee.getStatus());
        dto.setRegistrationDate(attendee.getRegistrationDate());
        dto.setTicketType(attendee.getTicketType());
        dto.setCompany(attendee.getCompany());
        dto.setNotes(attendee.getNotes());
        if (attendee.getEvent() != null) {
            dto.setEventRegistered(new EventCompactDto(
                    attendee.getEvent().getId(),
                    attendee.getEvent().getName(),
                    attendee.getEvent().getLocation(),
                    attendee.getEvent().getDateTime()
            ));
        } else {
            dto.setEventRegistered(null);
        }
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
