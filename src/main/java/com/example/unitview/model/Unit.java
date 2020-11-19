package com.example.unitview.model;

import com.example.unitview.util.UnitUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_art_nr")
    @OrderBy("id")
    @BatchSize(size = 100)
    private List<Part> subUnits;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "compositdse",
            joinColumns = @JoinColumn(name = "art_nr", referencedColumnName = "art_nr"))
    @OrderBy("title asc, description asc")
    @BatchSize(size = 100)
    private List<Unit> parent;

    @OneToOne(fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "idgrdse")
    private UnitGroup group;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
    @BatchSize(size = 100)
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
