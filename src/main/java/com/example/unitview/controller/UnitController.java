package com.example.unitview.controller;

import com.example.unitview.model.TechProcess;
import com.example.unitview.model.Unit;
import com.example.unitview.service.TechProcService;
import com.example.unitview.service.UnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/api/units/")
public class UnitController {

    private static final Logger log = LoggerFactory.getLogger(UnitController.class);

    private static final int PAGE_SIZE = 20;

    private final UnitService unitService;
    private final TechProcService tpService;

    public UnitController(UnitService unitService, TechProcService tpService) {
        this.unitService = unitService;
        this.tpService = tpService;
    }

    @GetMapping("/{id}")
    public String getUnit(Model unitModel, @PathVariable int id) {
        Unit unit = unitService.findById(id);
        log.info("Got unit: {}", unit.toString());
        unitModel.addAttribute("unit", unit);
        return "unitPlain";
    }

    @GetMapping("/{id}/composition")
    public String getUnitWithSubUnits(Model model, @PathVariable int id) {
        Unit unit = unitService.findByIdWithSubUnits(id);
        log.info("Got unit: {} with {} sub-units", unit.toString(), unit.getSubUnits().size());
        model.addAttribute("unit", unit);
        return "unitComposition";
    }

    @GetMapping("/all")
    public String getAllUnits(Model model,
                              @RequestParam("page") Optional<Integer> pageNumber,
                              @RequestParam("search") Optional<String> query) {
        //todo open unit page if search count is 1
        int currentPage = pageNumber.orElse(1);
        String searchPattern = query.orElse(null);
        Page<Unit> units = searchPattern == null ?
                unitService.findAllPageable(PAGE_SIZE, currentPage - 1) :
                unitService.findAllMatching(searchPattern, PAGE_SIZE, currentPage - 1);
        model.addAttribute("units", units);
        int totalPages = units.getTotalPages();
        log.info("Got {} units from service, separated on {} pages", units.getTotalElements(), totalPages);
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "allUnits";
    }

    @GetMapping("/{id}/exploding")
    public String getComposition(Model model, @PathVariable("id") int id) {
        Unit unit = unitService.findByIdWithSubUnits(id);
        model.addAttribute("unit", unit);
        Map<Unit, Integer> explodedComposition = unitService.explodeUnit(unit);
        log.info("Decomposition finished with {} units", explodedComposition.values().size());
        model.addAttribute("explodedComp", explodedComposition);
        return "unitExploding";
    }

    @GetMapping("/{id}/tp")
    public String getTechProcess(Model model, @PathVariable("id") int id) {
        Unit unit = unitService.findByIdWithTp(id);
        unit.getTechProcesses().sort(TechProcess::compareTo);
        model.addAttribute("unit", unit);
        return "unitTechProc";
    }

    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }
}
