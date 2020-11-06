package com.example.unitview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unit_group")
public class UnitGroup {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    public UnitGroup() {
    }

    public UnitGroup(int id, String title) {
        this.id = id;
        this.title = title;
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

    public void setTitle(String name) {
        this.title = name;
    }

    @Override
    public String toString() {
        return "UnitGroup{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
