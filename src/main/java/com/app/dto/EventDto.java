package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
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
}
