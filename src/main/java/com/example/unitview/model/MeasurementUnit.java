package com.example.unitview.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "measurement")
@Data
public class MeasurementUnit {

    @Id
    private int id;

    @Column(name = "unit")
    private String shortName;

    @Column(name = "naming")
    private String fullName;

}
