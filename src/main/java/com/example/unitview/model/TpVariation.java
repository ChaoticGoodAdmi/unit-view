package com.example.unitview.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tech_proc_var")
@Data
@NoArgsConstructor
public class TpVariation {

    @Id
    @Column(name = "nvar")
    private int id;

    @Column(name = "type")
    private String type;
}
