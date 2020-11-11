package com.example.unitview.model;

import com.example.unitview.util.UnitUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "REFDSE")
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idgrdse")
    private UnitGroup group;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
    private List<TechProcess> techProcesses;

    public Unit() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
        this.id = UnitUtils.convertArticleToId(article);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UnitGroup getGroup() {
        return group;
    }

    public void setGroup(UnitGroup group) {
        this.group = group;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public List<Part> getSubUnits() {
        return subUnits == null ? new ArrayList<>() : subUnits;
    }

    public void setSubUnits(List<Part> subUnits) {
        this.subUnits = subUnits;
    }

    public List<TechProcess> getTechProcesses() {
        return techProcesses;
    }

    public void setTechProcesses(List<TechProcess> techProcesses) {
        this.techProcesses = techProcesses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Unit unit = (Unit) o;

        if (id != unit.id) return false;
        if (!article.equals(unit.article)) return false;
        if (!Objects.equals(title, unit.title)) return false;
        if (!Objects.equals(description, unit.description)) return false;
        if (!Objects.equals(notes, unit.notes)) return false;
        return group.equals(unit.group);
    }

    @Override
    public int hashCode() {
        int result = article.hashCode();
        result = 31 * result + id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + group.hashCode();
        return result;
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
