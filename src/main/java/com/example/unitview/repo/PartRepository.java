package com.example.unitview.repo;

import com.example.unitview.model.Part;

import java.util.List;

public interface PartRepository {

    List<Part> findSubUnits(String art);

}
