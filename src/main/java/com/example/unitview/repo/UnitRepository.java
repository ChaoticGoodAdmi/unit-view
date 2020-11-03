package com.example.unitview.repo;

import com.example.unitview.model.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {

    Optional<Unit> findByArticle(String article);
    Page<Unit> findAllByArticleContaining(String includePattern, Pageable pageable);
}
