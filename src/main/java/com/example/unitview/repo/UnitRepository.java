package com.example.unitview.repo;

import com.example.unitview.model.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {

    Optional<Unit> findByArticle(String article);

    Page<Unit> findAllByArticleContaining(String includePattern, Pageable pageable);

    @Query("SELECT u FROM Unit u " +
            "WHERE u.article LIKE %:pattern% " +
            "OR u.title LIKE %:pattern% " +
            "OR u.description LIKE %:pattern%")
    Page<Unit> findBySearchPattern(@Param("pattern") String searchPattern, Pageable pageable);
}
