package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeDto {
    @JsonProperty
    private String name;
    @JsonProperty
    private String email;
    @JsonProperty
    private String phoneNumber;

    @JsonProperty
    private EventCompactDto eventRegistered;
}
