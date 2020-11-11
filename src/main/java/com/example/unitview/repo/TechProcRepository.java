package com.example.unitview.repo;

import com.example.unitview.model.TechProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TechProcRepository extends JpaRepository<TechProcess, Integer> {

    @Query("SELECT tp FROM TechProcess tp LEFT JOIN FETCH TechOperation")
    TechProcess findByIdWithOperations(int id);
}
