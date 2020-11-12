package com.example.unitview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tech_proc_oper_var")
public class OperationVariation {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    public OperationVariation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String type) {
        this.title = type;
    }
}
