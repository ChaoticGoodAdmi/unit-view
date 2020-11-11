package com.example.unitview.service;

import com.example.unitview.model.TechProcess;
import com.example.unitview.repo.TechProcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechProcService {

    private final TechProcRepository tpRepo;

    @Autowired
    public TechProcService(TechProcRepository tpRepo) {
        this.tpRepo = tpRepo;
    }

    public TechProcess findTpWithOperations(int id) {
        return tpRepo.findByIdWithOperations(id);
    }

}
