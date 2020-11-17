package com.example.unitview.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tech_proc_oper_var")
@Data
@NoArgsConstructor
public class OperationVariation {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;
}
