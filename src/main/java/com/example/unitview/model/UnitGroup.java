package com.example.unitview.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unit_group")
@Data
@RequiredArgsConstructor
public class UnitGroup {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;
}
