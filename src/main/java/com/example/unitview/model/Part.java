package com.example.unitview.model;

public class Part {

    private Unit unit;
    private int quantity;

    public Part(Unit unit, int quantity) {
        this.unit = unit;
        this.quantity = quantity;
    }

    public Part() {
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
