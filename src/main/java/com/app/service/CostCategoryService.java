package com.app.service;

import com.app.model.CostCategory;
import com.app.repository.CostCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CostCategoryService {

    private final CostCategoryRepository costCategoryRepository;

    @Autowired
    public CostCategoryService(CostCategoryRepository costCategoryRepository) {
        this.costCategoryRepository = costCategoryRepository;
    }

    @Transactional
    public CostCategory createOrUpdateCostCategory(CostCategory costCategory) {
        // Here you could calculate and set the total cost of this category
        // by summing up the costs of its CostSources, if needed.
        return costCategoryRepository.save(costCategory);
    }

    public Optional<CostCategory> findById(Long id) {
        return costCategoryRepository.findById(id);
    }

    public List<CostCategory> findAll() {
        return costCategoryRepository.findAll();
    }

    @Transactional
    public void deleteCostCategory(Long id) {
        costCategoryRepository.deleteById(id);
    }
}
