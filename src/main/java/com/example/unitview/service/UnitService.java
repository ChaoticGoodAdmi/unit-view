package com.example.unitview.service;

import com.example.unitview.model.Unit;
import com.example.unitview.repo.UnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UnitService {

    private static final Logger log = LoggerFactory.getLogger(UnitService.class);

    private final UnitRepository repository;

    @Autowired
    public UnitService(UnitRepository repository) {
        this.repository = repository;
    }

    public Unit findByArticle(int art) {
        log.info("Service is loading unit by article {}", art);
        return repository.findByArticle(art + "/1").orElseThrow(() -> new IllegalArgumentException("Unit " + art + " not found in repo"));
    }

    public Page<Unit> findAllPageable(int pageNumber, int pageSize) {
        log.info("Service getting all units (paging)");
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
        return repository.findAll(pageable);
    }

    public Page<Unit> findAllPageableInclude(String includePattern, int pageSize, int pageNumber) {
        log.info("Service getting all units (excluding)");
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
        return repository.findAllByArticleContaining(includePattern, pageable);
    }
}
