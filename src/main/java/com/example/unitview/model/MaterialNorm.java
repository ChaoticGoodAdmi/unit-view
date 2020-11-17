package com.example.unitview.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "material_norm")
@Data
@NoArgsConstructor
public class MaterialNorm {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "tech_proc_id")
    private TechProcess techProcess;

    @OneToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(name = "dimension_size")
    private String dimensionSize;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "mold_size")
    private String moldSize;

    @Column(name = "mold_size_num")
    private Integer moldSizeAddition;

    @Column(name = "norm")
    private Float quantity;
}
