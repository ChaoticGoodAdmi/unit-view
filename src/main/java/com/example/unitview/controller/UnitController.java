package com.example.unitview.controller;

import com.example.unitview.model.Unit;
import com.example.unitview.service.UnitService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/api/units/")
public class UnitController {

    private static final int PAGE_SIZE = 20;
    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping("/{art}")
    public String getUnit(Model unitModel, @PathVariable int art) {
        Unit unit = unitService.findByArticle(art);
        unitModel.addAttribute("unit", unit);
        return "unit";
    }

    @GetMapping("/all")
    public String getAllUnits(Model model, @RequestParam("page") Optional<Integer> pageNumber) {
        int currentPage = pageNumber.orElse(1);
        Page<Unit> units = unitService.findAllPageable(currentPage - 1, PAGE_SIZE);
        model.addAttribute("units", units);
        int totalPages = units.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "allUnits";
    }
}
