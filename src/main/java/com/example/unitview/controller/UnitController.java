package com.example.unitview.controller;

import com.example.unitview.model.Unit;
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
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/api/units/")
public class UnitController {

    private static final Logger log = LoggerFactory.getLogger(UnitController.class);

    private static final int PAGE_SIZE = 20;
    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
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

    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }
}
