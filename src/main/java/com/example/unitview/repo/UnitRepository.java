package com.example.unitview.repo;

import com.example.unitview.model.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {

    @Transactional
    Optional<Unit> findByArticle(String article);

    @Transactional
    @Query("SELECT u FROM Unit u " +
            "WHERE u.article LIKE %:pattern% " +
            "OR LOWER(u.title) LIKE %:pattern% " +
            "OR LOWER(u.description) LIKE %:pattern% " +
            "OR LOWER(u.group.title) LIKE %:pattern% " +
            "OR LOWER(CONCAT(u.title, ' ', u.description)) LIKE %:pattern%")
    Page<Unit> findBySearchPattern(@Param("pattern") String searchPattern, Pageable pageable);
}
