package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeDto {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String email;
    @JsonProperty
    private String phoneNumber;
    @JsonProperty
    private String status; // e.g., Registered, Checked-in, Cancelled
    @JsonProperty
    private LocalDateTime registrationDate;
    @JsonProperty
    private String ticketType; // e.g., VIP, General Admission, Student
    @JsonProperty
    private String company;
    @JsonProperty
    private String notes;
    @JsonProperty
    private EventCompactDto eventRegistered; // Event information
}
