package com.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ep_cost_source")
public class CostSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    private String category;
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @JsonCreator
    public CostSource(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("cost") Double cost,
            @JsonProperty("category") String category) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.category = category;
    }


}
