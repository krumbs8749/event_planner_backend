package com.app.controller;

import com.app.dto.*;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events); // This automatically wraps the list in a ResponseEntity with OK status
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Event>> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        System.out.println(event);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(event);
    }

    @PostMapping("/create")
    public Event createEvent(@RequestBody EventDto event) {
        return eventService.createOrUpdateEvent(event);
    }

    @PutMapping("/update")
    public ResponseEntity<Event> updateEvent(@RequestBody EventDto eventDetails) {
        // Update the event
        return ResponseEntity.ok(eventService.createOrUpdateEvent(eventDetails));
    }

    @DeleteMapping("/delete/{eventName}/{location}/{datetime}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable String eventName,
            @PathVariable String location,
            @PathVariable String datetime) {
        eventService.deleteEvent(eventName, location, datetime);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/costs/{eventId}")
    public void addOrUpdateCost(@PathVariable Long eventId, @RequestBody CostSourceDto costSourceDto) {
        eventService.addOrUpdateCost(eventId, costSourceDto);
    }

    @PutMapping("/update/tasks/{eventId}")
    public void addOrUpdateTask(@PathVariable Long eventId, @RequestBody TaskDto taskDto) {
        eventService.addOrUpdateTask(eventId, taskDto);
    }

    @PutMapping("/update/attendees/{eventId}")
    public void addOrUpdateAttendee(@PathVariable Long eventId, @RequestBody AttendeeDto attendeeDto) {
        eventService.addOrUpdateAttendee(eventId, attendeeDto);
    }
}
