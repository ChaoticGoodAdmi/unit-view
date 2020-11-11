package com.example.unitview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tech_proc_var")
public class TpVariation {

    @Id
    @Column(name = "nvar")
    private int id;

    @Column(name = "type")
    private String type;

    public TpVariation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
