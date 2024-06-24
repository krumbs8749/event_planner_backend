package com.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ep_attendee")
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String status; // e.g., Registered, Checked-in, Cancelled

    private LocalDateTime registrationDate;
    private String ticketType; // e.g., VIP, General Admission, Student
    private String company;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event; // Reference to the associated event
}
