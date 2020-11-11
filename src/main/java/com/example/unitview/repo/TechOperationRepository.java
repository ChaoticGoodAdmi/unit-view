package com.example.unitview.repo;

import com.example.unitview.model.TechOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechOperationRepository extends JpaRepository<TechOperation, Integer> {

}
