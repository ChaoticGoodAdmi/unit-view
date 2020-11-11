package com.example.unitview.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tech_proc")
@SecondaryTables({
        @SecondaryTable(name = "tech_proc_act", pkJoinColumns = @PrimaryKeyJoinColumn(name = "tp_id"))
})
public class TechProcess implements Comparable<TechProcess> {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article")
    private Unit unit;

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "variation")
    private TpVariation variation;

    @Column(name = "is_active", table = "tech_proc_act")
    private boolean active;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "techProcess")
    @Fetch(value = FetchMode.JOIN)
    private List<TechOperation> operations;

    public TechProcess() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TpVariation getVariation() {
        return variation;
    }

    public void setVariation(TpVariation variation) {
        this.variation = variation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<TechOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<TechOperation> operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "TechProcess{" +
                "id=" + id +
                ", unit=" + unit +
                ", title='" + title + '\'' +
                ", variation='" + variation.getType()
                + '\'' +
                ", isActive=" + active +
                '}';
    }

    @Override
    public int compareTo(TechProcess tp) {
        if (this.active) {
            return tp.isActive() ? 0 : -1;
        } else {
            if (tp.isActive()) {
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
