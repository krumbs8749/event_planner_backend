package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CostSourceDto {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private String category;
    @JsonProperty
    private Double cost;
}
