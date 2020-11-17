package com.example.unitview.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Part {

    private Unit unit;
    private int quantity;

    public Part(Unit unit, int quantity) {
        this.unit = unit;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Part{" +
                "unit=" + unit +
                ", quantity=" + quantity +
                '}';
    }
}
