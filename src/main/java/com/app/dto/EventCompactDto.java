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
public class EventCompactDto {
    @JsonProperty
    private String name;
    @JsonProperty
    private String location;
    @JsonProperty
    private LocalDateTime dateTime;
}
