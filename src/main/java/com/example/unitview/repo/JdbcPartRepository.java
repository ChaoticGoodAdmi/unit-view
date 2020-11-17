package com.example.unitview.repo;

import com.example.unitview.model.Part;
import com.example.unitview.model.Unit;
import com.example.unitview.util.UnitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcPartRepository implements PartRepository {

    private final JdbcTemplate template;
    private final UnitGroupRepository unitGroupRepo;
    private RowMapper<Part> rowMapper;

    @Autowired
    public JdbcPartRepository(JdbcTemplate template, UnitGroupRepository unitGroupRepo) {
        this.template = template;
        this.unitGroupRepo = unitGroupRepo;
        createRowMapper();
    }

    @Override
    public List<Part> findSubUnits(String art) {
        return template.query(
                "SELECT DISTINCT cd.art_nr, cd.quantdse, r.* " +
                        "FROM compositdse cd " +
                        "INNER JOIN refdse r " +
                        "ON r.art_nr = cd.art_nr " +
                        "WHERE cd.parent_art_nr = ?" +
                        "ORDER BY cd.art_nr",
                rowMapper, art);
    }

    @Override
    public List<Unit> findParentUnits(String art) {
        return template.query(
                "SELECT DISTINCT r.* " +
                        "FROM compositdse cd " +
                        "INNER JOIN refdse r " +
                        "ON r.art_nr = cd.parent_art_nr " +
                        "WHERE cd.art_nr = ? " +
                        "ORDER BY r.art_nr",
                (resultSet, i) -> Unit.builder()
                        .article(resultSet.getString("art_nr"))
                        .id(UnitUtils.convertArticleToId(resultSet.getString("art_nr")))
                        .title(resultSet.getString("art_bez"))
                        .description(resultSet.getString("art_bez2"))
                        .notes(resultSet.getString("notes"))
                        .group(unitGroupRepo.getOne(resultSet.getInt("idgrdse")))
                        .build(),
                art);
    }

    private void createRowMapper() {
        rowMapper = (resultSet, i) -> {
            Unit unit = new Unit();
            unit.setArticle(resultSet.getString("art_nr"));
            unit.setTitle(resultSet.getString("art_bez"));
            unit.setDescription(resultSet.getString("art_bez2"));
            unit.setGroup(unitGroupRepo.getOne(resultSet.getInt("idgrdse")));
            unit.setNotes(resultSet.getString("notes"));
            return new Part(unit, resultSet.getInt("quantdse"));
        };
    }
}