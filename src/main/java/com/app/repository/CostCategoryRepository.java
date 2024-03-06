package com.app.repository;

import com.app.model.CostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostCategoryRepository extends JpaRepository<CostCategory, Long> {
}