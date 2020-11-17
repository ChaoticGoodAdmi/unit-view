package com.example.unitview.service;

import com.example.unitview.model.Part;
import com.example.unitview.model.TechOperation;
import com.example.unitview.model.TechProcess;
import com.example.unitview.model.Unit;
import com.example.unitview.repo.PartRepository;
import com.example.unitview.repo.UnitRepository;
import com.example.unitview.util.TechProcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnitService {

    private static final Logger log = LoggerFactory.getLogger(UnitService.class);

    private final UnitRepository unitRepo;
    private final PartRepository partRepo;

    @Autowired
    public UnitService(UnitRepository unitRepo, PartRepository partRepo) {
        this.unitRepo = unitRepo;
        this.partRepo = partRepo;
    }

    @Cacheable("units")
    public Unit findById(int id) {
        return findByArticle(String.valueOf(id));
    }

    @Transactional
    @Cacheable("units")
    public Unit findByArticle(String article) throws IllegalArgumentException {
        log.info("Service is loading unit by article {}", article);
        Unit unit;
        try {
            unit = unitRepo.findByArticle(article + "/1").orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            unit = unitRepo.findByArticle(article + "/5").orElseThrow(IllegalArgumentException::new);
        }
        return unit;
    }

    @Cacheable("unitPages")
    public Page<Unit> findAllPageable(int pageSize, int pageNumber) {
        log.info("Service getting all units (paging)");
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.unsorted());
        return unitRepo.findAll(pageable);
    }

    @Cacheable("unitSearch")
    public Page<Unit> findAllMatching(String searchPattern, int pageSize, int pageNumber) {
        log.info("Service getting all units matching {}", searchPattern);
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.by("article"));
        return unitRepo.findBySearchPattern(searchPattern.toLowerCase().replace("*", "%"), pageable);
    }

    @Cacheable("subUnits")
    public Unit findByIdWithSubUnits(int id, String queryParam) {
        log.info("Service is loading unit with sub-units by article {}", id);
        Unit unit = findById(id);
        log.info("Service has loaded unit: {}", unit.toString());
        List<Part> subUnits = partRepo.findSubUnits(unit.getArticle());
        unit.setSubUnits("".equals(queryParam) ? subUnits : filterParts(subUnits, queryParam));
        return unit;
    }

    public Unit findByUnitWithSubUnits(Unit unit) {
        return findByIdWithSubUnits(unit.getId(), "");
    }

    @CacheEvict("subUnits")
    public List<Part> filterParts(List<Part> subUnits, String queryParam) {
        List<String> parameters = List.of(queryParam.split("\\*"));
        return subUnits.stream()
                .filter(part -> {
                    Unit unit = part.getUnit();
                    String unitAsString = unit.getArticle() + " " +
                            Optional.ofNullable(unit.getTitle()).orElse("").toLowerCase() + " " +
                            unit.getDescription().toLowerCase() + " " +
                            unit.getGroup().getTitle().toLowerCase();
                    return parameters.stream().allMatch(unitAsString::contains);
                })
                .collect(Collectors.toList());
    }

    @Cacheable("unitWithTp")
    public Unit findByIdWithTp(int id) {
        Optional<Unit> unit = unitRepo.findByArticleWithTp(id + "/1");
        return unit.isEmpty() ? unitRepo.findByArticleWithTp(id + "/5")
                .orElseThrow(() -> new IllegalArgumentException("Unit " + id + " not found in repo")) : unit.get();
    }

    @Transactional
    @Cacheable("explodedUnit")
    public Map<Unit, Integer> explodeUnit(Unit original) {
        Map<Unit, Integer> exploded = new HashMap<>();
        final int DEFAULT_ORIGIN_QUANTITY = 1;
        explodeUnitRecursively(original, DEFAULT_ORIGIN_QUANTITY, DEFAULT_ORIGIN_QUANTITY, exploded);
        return exploded;
    }

    private void explodeUnitRecursively(Unit unitToExplode, int quantity, int parentQuantity, Map<Unit, Integer> exploded) {
        List<Part> subUnits = unitToExplode.getSubUnits();
        if (subUnits.size() > 0) {
            for (Part part : subUnits) {
                Unit childUnit = part.getUnit();
                int childQuantity = part.getQuantity();
                explodeUnitRecursively(findByUnitWithSubUnits(childUnit), childQuantity * parentQuantity, quantity, exploded);
            }
        }
        putOrAdd(unitToExplode, quantity * parentQuantity, exploded);
    }

    private void putOrAdd(Unit unit, int quantity, Map<Unit, Integer> unitMap) {
        unitMap.put(unit, unitMap.containsKey(unit) ? quantity + unitMap.get(unit) : quantity);
    }

    public String getResponsibleDepartment(Unit unit) {
        List<TechProcess> techProcesses = unit.getTechProcesses();
        try {
            TechProcess sortingTp = TechProcUtils.getSortingTechProcess(techProcesses);
            TechOperation lastSortingOperation = TechProcUtils.getLastTechOperation(sortingTp, null);
            TechProcess activeTp = TechProcUtils.getActiveTechProcess(techProcesses);
            TechOperation lastActiveOperation =
                    TechProcUtils.getLastTechOperation(activeTp, lastSortingOperation.getDepartment());
            StringBuilder responsibleDept = new StringBuilder();
            responsibleDept.append(lastSortingOperation.getDepartment().getCode());
            responsibleDept.append("-");
            if (lastActiveOperation.getLocalDept() != null) {
                responsibleDept.append(lastActiveOperation.getLocalDept());
            } else {
                responsibleDept.append(0);
            }
            return responsibleDept.toString();
        } catch (NullPointerException npe) {
            return "< не определено >";
        }
    }

    @Cacheable("appliance")
    public List<Unit> findParentUnits(Unit unit) {
        return partRepo.findParentUnits(unit.getArticle());
    }
}
