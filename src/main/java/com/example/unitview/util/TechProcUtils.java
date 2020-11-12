package com.example.unitview.util;

import com.example.unitview.model.Department;
import com.example.unitview.model.TechOperation;
import com.example.unitview.model.TechProcess;

import java.util.Comparator;
import java.util.List;

public class TechProcUtils {

    public static final int SORTING_TP_ID = 14;

    public static TechOperation getLastTechOperation(TechProcess techProcess, Department department) {
        return department == null ? techProcess.getOperations().stream()
                .max(Comparator.comparing(TechOperation::getCode))
                .orElse(null) :
                techProcess.getOperations().stream()
                .filter(operation -> operation.getDepartment().getId() == department.getId())
                .max(Comparator.comparing(TechOperation::getCode))
                .orElse(null);
    }

    public static TechProcess getSortingTechProcess(List<TechProcess> techProcesses) {
        return techProcesses.stream()
                .filter(techProcess -> techProcess.getVariation().getId() == SORTING_TP_ID)
                .findFirst()
                .orElse(null);
    }

    public static TechProcess getActiveTechProcess(List<TechProcess> techProcesses) {
        return techProcesses.stream()
                .filter(TechProcess::isActive)
                .findFirst()
                .orElse(null);
    }
}
