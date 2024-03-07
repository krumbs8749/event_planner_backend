package com.app.controller;

import com.app.dto.AttendeeDto;
import com.app.model.Attendee;
import com.app.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;

    @Autowired
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    // Endpoint to create or update an attendee
    @PostMapping("/create")
    public Attendee createAttendee(@RequestBody AttendeeDto attendeeDto) {
       return attendeeService.createAttendee(attendeeDto);

    }

    //@PostMapping("/update")
    //public Attendee updateAttendee(@RequestBody AttendeeDto attendeeDto) {
    //    return attendeeService.createOrUpdateAttendee(attendeeDto);
    //}

    // Endpoint to get an attendee by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<AttendeeDto> getAttendeeById(@PathVariable Long id) {
        return attendeeService.findById(id)
                .map(attendeeService::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to list all attendees
    @GetMapping("/getAll")
    public ResponseEntity<List<AttendeeDto>> getAllAttendees() {
        List<AttendeeDto> attendees = attendeeService.findAll().stream()
                .map(attendeeService::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(attendees);
    }

    // Endpoint to delete an attendee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAttendee(@PathVariable Long id) {
        attendeeService.deleteAttendee(id);
        return ResponseEntity.ok().build();
    }
}
