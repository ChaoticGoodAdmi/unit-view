package com.example.unitview.repo;

import com.example.unitview.model.Part;
import com.example.unitview.model.Unit;

import java.util.List;

public interface PartRepository {

    List<Part> findSubUnits(String art);

    List<Unit> findParentUnits(String art);

}
