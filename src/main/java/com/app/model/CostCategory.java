package com.app.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ep_cost_category")
public class CostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double totalCost;

    @OneToMany(mappedBy = "category")
    private List<CostSource> costSources;

    // Constructors, getters, and setters
}
