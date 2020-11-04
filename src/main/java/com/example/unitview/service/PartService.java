package com.example.unitview.service;

import com.example.unitview.repo.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartService {

    private final PartRepository repository;

    @Autowired
    public PartService(PartRepository repository) {
        this.repository = repository;
    }
}
