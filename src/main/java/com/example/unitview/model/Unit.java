package com.example.unitview.model;

import com.example.unitview.util.UnitUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "REFDSE")
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Unit {

    @Id
    @Column(name = "ART_NR")
    @Access(value = AccessType.PROPERTY)
    private String article;

    @Transient
    private int id;

    @Column(name = "ART_BEZ")
    private String title;

    @Column(name = "ART_BEZ2")
    private String description;

    @Column(name = "notes")
    private String notes;

    @Transient
    private List<Part> subUnits;

    @Transient
    private List<Unit> parentUnits;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idgrdse")
    private UnitGroup group;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
    private List<TechProcess> techProcesses;

    public Unit() {

    }

    public void setArticle(String article) {
        this.article = article;
        this.id = UnitUtils.convertArticleToId(article);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "article='" + article + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", group=" + group + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
