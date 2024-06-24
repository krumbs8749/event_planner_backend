package com.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ep_event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String description;
    private String location;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private Double totalCost;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CostSource> costs;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Task> tasks;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Attendee> attendees;

    private Integer totalSeats;
    private Integer totalRegistration;

    @Enumerated(EnumType.STRING)
    private EventStatus status = EventStatus.LIVE;
}

