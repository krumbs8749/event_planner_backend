package com.app.controller;

import com.app.model.Event;
import com.app.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@CrossOrigin
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Event>> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(event);
    }

    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createOrUpdateEvent(event);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        // Update the event
        return ResponseEntity.ok(eventService.createOrUpdateEvent(eventDetails));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
