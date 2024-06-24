package com.app.service;

import com.app.dto.*;
import com.app.model.*;
import com.app.repository.AttendeeRepository;
import com.app.repository.CostSourceRepository;
import com.app.repository.EventRepository;
import com.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;
    private final CostSourceRepository costSourceRepository;
    private final TaskRepository taskRepository;
    private final OwnerService ownerService;

    @Autowired
    public EventService(EventRepository eventRepository, AttendeeRepository attendeeRepository,
                        CostSourceRepository costSourceRepository, TaskRepository taskRepository,
                        OwnerService ownerService) {
        this.eventRepository = eventRepository;
        this.attendeeRepository = attendeeRepository;
        this.costSourceRepository = costSourceRepository;
        this.taskRepository = taskRepository;
        this.ownerService = ownerService;
    }

    @Transactional
    public Event createOrUpdateEvent(EventDto eventDto) {
        // Check whether all necessary data is available
        if (eventDto.getName() == null || eventDto.getDescription() == null ||
                eventDto.getLocation() == null || eventDto.getDateTime() == null ||
                eventDto.getOwner() == null) {
            throw new IllegalArgumentException("Missing data for the event");
        }

        Event event = eventDto.getId() != null ? eventRepository.findById(Long.valueOf(eventDto.getId())).orElse(new Event()) : new Event();

        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setLocation(eventDto.getLocation());
        event.setDateTime(eventDto.getDateTime());
        event.setTotalCost(eventDto.getTotalCost());
        event.setTotalSeats(eventDto.getTotalSeats());
        event.setTotalRegistration(eventDto.getTotalRegistration());
        event.setStatus(eventDto.getStatus());

        Owner owner = ownerService.saveOrUpdateOwner(eventDto.getOwner());
        event.setOwner(owner);

        return eventRepository.save(event);
    }

    @Transactional
    public void addOrUpdateCost(Long eventId, CostSourceDto costSourceDto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));

        CostSource costSource = costSourceDto.getId() != null ? costSourceRepository.findById(costSourceDto.getId()).orElse(new CostSource()) : new CostSource();
        costSource.setName(costSourceDto.getName());
        costSource.setDescription(costSourceDto.getDescription());
        costSource.setCost(costSourceDto.getCost());
        costSource.setCategory(costSourceDto.getCategory());
        costSource.setEvent(event);

        costSourceRepository.save(costSource);
    }

    @Transactional
    public void addOrUpdateTask(Long eventId, TaskDto taskDto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));

        Task task = taskDto.getId() != null ? taskRepository.findById(taskDto.getId()).orElse(new Task()) : new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setCost(taskDto.getCost());
        task.setStatus(taskDto.getStatus());
        task.setDeadline(taskDto.getDeadline());
        task.setPriority(taskDto.getPriority());
        task.setAssignee(taskDto.getAssignee());
        task.setEvent(event);

        taskRepository.save(task);
    }

    @Transactional
    public void addOrUpdateAttendee(Long eventId, AttendeeDto attendeeDto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));

        Attendee attendee = attendeeDto.getId() != null ? attendeeRepository.findById(attendeeDto.getId()).orElse(new Attendee()) : new Attendee();
        attendee.setName(attendeeDto.getName());
        attendee.setEmail(attendeeDto.getEmail());
        attendee.setPhoneNumber(attendeeDto.getPhoneNumber());
        attendee.setStatus(attendeeDto.getStatus());
        attendee.setRegistrationDate(attendeeDto.getRegistrationDate());
        attendee.setTicketType(attendeeDto.getTicketType());
        attendee.setCompany(attendeeDto.getCompany());
        attendee.setNotes(attendeeDto.getNotes());
        attendee.setEvent(event);

        attendeeRepository.save(attendee);
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
        LocalDateTime datetime = LocalDateTime.parse(datetimeStr);
        eventRepository.deleteByNameAndLocationAndDateTime(eventName, location, datetime);
    }

    @Transactional
    public void addAttendeeToEvent(Long eventId, Long attendeeId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found with id: " + attendeeId));

        event.setTotalRegistration(event.getTotalRegistration() + 1);
        attendee.setEvent(event); // Assuming Attendee has a 'setEvent(Event event)' method
        eventRepository.save(event);
    }

    // Additional business logic methods as required
}
