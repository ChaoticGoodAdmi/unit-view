package com.example.unitview.service;

import com.example.unitview.model.Part;
import com.example.unitview.model.Unit;
import com.example.unitview.repo.PartRepository;
import com.example.unitview.repo.UnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    private static final Logger log = LoggerFactory.getLogger(UnitService.class);

    private final UnitRepository unitRepo;
    private final PartRepository partRepo;

    @Autowired
    public UnitService(UnitRepository unitRepo, PartRepository partRepo) {
        this.unitRepo = unitRepo;
        this.partRepo = partRepo;
    }

    public Unit findById(int id) {
        return findByArticle(id + "/1");
    }

    public Unit findByArticle(String article) {
        log.info("Service is loading unit by article {}", article);
        return unitRepo.findByArticle(article).orElseThrow(() -> new IllegalArgumentException("Unit " + article + " not found in repo"));
    }

    public Page<Unit> findAllPageable(int pageNumber, int pageSize) {
        log.info("Service getting all units (paging)");
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
        return unitRepo.findAll(pageable);
    }

    public Page<Unit> findAllPageableInclude(String includePattern, int pageSize, int pageNumber) {
        log.info("Service getting all units (excluding)");
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
        return unitRepo.findAllByArticleContaining(includePattern, pageable);
    }

    public Unit findByIdWithSubUnits(int id) {
        log.info("Service is loading unit with sub-units by article {}", id);
        Unit unit = findById(id);
        log.info("Service has loaded unit: {}", unit.toString());
        List<Part> subUnits = partRepo.findSubUnits(unit.getArticle());
        unit.setSubUnits(subUnits);
        return unit;
    }
}
