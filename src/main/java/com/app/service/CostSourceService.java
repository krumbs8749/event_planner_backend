package com.app.service;

import com.app.model.CostSource;
import com.app.repository.CostSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CostSourceService {

    private final CostSourceRepository costSourceRepository;

    @Autowired
    public CostSourceService(CostSourceRepository costSourceRepository) {
        this.costSourceRepository = costSourceRepository;
    }

    @Transactional
    public CostSource createOrUpdateCostSource(CostSource costSource) {
        // Business logic before saving can be added here
        return costSourceRepository.save(costSource);
    }

    public Optional<CostSource> findById(Long id) {
        return costSourceRepository.findById(id);
    }

    public List<CostSource> findAll() {
        return costSourceRepository.findAll();
    }

    @Transactional
    public void deleteCostSource(Long id) {
        costSourceRepository.deleteById(id);
    }
}

