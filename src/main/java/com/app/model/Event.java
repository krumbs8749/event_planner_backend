package com.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
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
    private Date dateTime;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private Double totalCost;

    @OneToMany(mappedBy = "event")
    private List<CostSource> costs;

    @OneToMany(mappedBy = "event")
    private List<Task> tasks;

    private Integer totalSeats;
    private Integer totalRegistration;

}
