package com.example.unitview.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tech_proc")
@SecondaryTables({
        @SecondaryTable(name = "tech_proc_act", pkJoinColumns = @PrimaryKeyJoinColumn(name = "tp_id"))
})
@Data
public class TechProcess implements Comparable<TechProcess> {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article")
    private Unit unit;

    @Column(name = "title")
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variation")
    private TpVariation variation;

    @Column(name = "is_active", table = "tech_proc_act")
    private boolean active;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "techProcess")
    @Fetch(value = FetchMode.JOIN)
    @OrderBy("code ASC")
    private List<TechOperation> operations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "techProcess")
    private List<MaterialNorm> materialNorms;

    @Override
    public int compareTo(TechProcess tp) {
        if (this.active) {
            return tp.active ? 0 : -1;
        } else {
            if (tp.active) {
                return 1;
            } else {
                if (this.variation.getId() == 14) {
                    return tp.variation.getId() == 14 ? 0 : -1;
                } else {
                    return tp.variation.getId() == 14 ? 1 : 0;
                }
            }
        }
    }
}
