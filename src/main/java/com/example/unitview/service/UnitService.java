package com.example.unitview.service;

import com.example.unitview.model.Unit;
import com.example.unitview.repo.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {

    private final UnitRepository repository;

    @Autowired
    public UnitService(UnitRepository repository) {
        this.repository = repository;
    }

    public Unit findByArticle(int art) {
        return repository.findByArticle(art + "/1").orElseThrow(() -> new IllegalArgumentException("404 NOT FOUND"));
    }

    public Page<Unit> findAllPageable(int pageNumber, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
        return repository.findAll(pageable);
    }
}
