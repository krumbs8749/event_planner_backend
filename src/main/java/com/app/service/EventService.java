package com.app.service;

import com.app.dto.EventDto;
import com.app.model.Attendee;
import com.app.model.Event;
import com.app.model.Owner;
import com.app.repository.AttendeeRepository;
import com.app.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository  attendeeRepository;

    private final OwnerService ownerService;



    @Autowired
    public EventService(EventRepository eventRepository, AttendeeRepository attendeeRepository, OwnerService ownerService) {
        this.eventRepository = eventRepository;
        this.attendeeRepository = attendeeRepository;
        this.ownerService = ownerService;
    }

    @Transactional
    public Event createOrUpdateEvent(EventDto eventDto) {
        // Check whether all necessary data is available
        if (eventDto.getName() == null || eventDto.getDescription() == null ||
                eventDto.getLocation() == null || eventDto.getDateTime() == null ||
                eventDto.getOwner() == null ) {
            throw new IllegalArgumentException("Missing data for the event");
        }
        // Check if a task with the same name and event already exists
        Optional<Event> existingEvent = eventRepository.findByNameAndLocationAndDateTime(eventDto.getName(), eventDto.getLocation(), eventDto.getDateTime());

        Event event = new Event();

        if (existingEvent.isPresent()) {
            // If the event exists, update it
            event = existingEvent.get();

        }

        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setLocation(eventDto.getLocation());
        event.setDateTime(eventDto.getDateTime());
        event.setTotalCost(eventDto.getTotalCost());
        event.setTotalSeats(eventDto.getTotalSeats());
        event.setTotalRegistration(eventDto.getTotalRegistration());

        Owner owner = ownerService.saveOrUpdateOwner(eventDto.getOwner());
        event.setOwner(owner);


        // If the event does not exist or has no ID, create a new event
        return eventRepository.save(event);
    }


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Optional<Event> getEventByNameAndLocationAndDateTime(String name, String location, LocalDateTime dateTime) {
        return eventRepository.findByNameAndLocationAndDateTime(name, location, dateTime);
    }

    @Transactional
    public void deleteEvent(String eventName, String location, String datetimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime datetime = LocalDateTime.parse(datetimeStr, formatter);
        eventRepository.deleteByNameAndLocationAndDateTime(eventName, location, datetime);
    }

    @Transactional
    public void addAttendeeToEvent(Long eventId, Long attendeeId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found with id: " + attendeeId));

        event.setTotalRegistration(event.getTotalRegistration() + 1);
        attendee.setEventRegistered(event); // Assuming Attendee has a 'setEvent(Event event)' method
        eventRepository.save(event);
    }




    // Additional business logic methods as required
}