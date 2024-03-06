package com.app.service;

import com.app.model.Event;
import com.app.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public Event createOrUpdateEvent(Event event) {
        // Check whether all necessary data is available
        if (event.getName() == null || event.getDescription() == null ||
                event.getLocation() == null || event.getDateTime() == null ||
                event.getOwner() == null ) {
            throw new IllegalArgumentException("Missing data for the event");
        }

        // Check if a task with the same name and event already exists
        Optional<Event> existingEvent = eventRepository.findByNameAndLocationAndDateTime(event.getName(), event.getLocation(), event.getDateTime());
        if (existingEvent.isPresent()) {
            // If the event exists, update it
            Event updatedEvent = existingEvent.get();

            updatedEvent.setName(event.getName());
            updatedEvent.setDescription(event.getDescription());
            updatedEvent.setLocation(event.getLocation());
            updatedEvent.setDateTime(event.getDateTime());
            updatedEvent.setOwner(event.getOwner());
            updatedEvent.setTotalCost(event.getTotalCost());
            updatedEvent.setTasks(event.getTasks());
            updatedEvent.setTotalSeats(event.getTotalSeats());
            updatedEvent.setTotalRegistration(event.getTotalRegistration());

            return eventRepository.save(updatedEvent);
        }

        // If the event does not exist or has no ID, create a new event
        return eventRepository.save(event);
    }


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }


    // Additional business logic methods as required
}