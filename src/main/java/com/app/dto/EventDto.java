package com.app.dto;

import com.app.model.EventStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private String location;
    @JsonProperty
    private LocalDateTime dateTime;
    @JsonProperty
    private OwnerDto owner;
    @JsonProperty
    private Double totalCost;
    @JsonProperty
    private Integer totalSeats;
    @JsonProperty
    private Integer totalRegistration;
    @JsonProperty
    private List<AttendeeDto> attendees;
    @JsonProperty
    private EventStatus status;
}
