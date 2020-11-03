package com.example.unitview.model;

import com.example.unitview.util.UnitUtils;

import javax.persistence.*;

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

    @Column(name = "IDGRDSE")
    private int groupId;

    @Column(name = "notes")
    private String notes;

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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    @Override
    public String toString() {
        return "Unit{" +
                "article='" + article + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", groupId=" + groupId +
                ", notes='" + notes + '\'' +
                '}';
    }
}
