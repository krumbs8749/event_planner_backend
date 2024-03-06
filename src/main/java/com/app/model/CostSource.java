package com.app.model;
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CostCategory category;
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
