package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
    @JsonProperty
    private String name;
    @JsonProperty
    private String email;
    @JsonProperty
    private String phoneNumber;
}
