package com.example.unitview.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "compositdse")
@Data
@NoArgsConstructor
public class Part {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_nr")
    private Unit unit;

    @Column(name = "quantdse")
    private int quantity;

}
