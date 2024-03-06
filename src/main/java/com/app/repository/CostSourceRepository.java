package com.app.repository;

import com.app.model.CostSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostSourceRepository extends JpaRepository<CostSource, Long> {

}