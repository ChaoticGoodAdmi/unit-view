package com.example.unitview.repo;

import com.example.unitview.model.UnitGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitGroupRepository extends JpaRepository<UnitGroup, Integer> {

}
