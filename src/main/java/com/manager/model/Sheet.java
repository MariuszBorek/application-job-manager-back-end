package com.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manager.enums.DrawingType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Sheet {

    @Id
    @GeneratedValue
    private Integer id;
    private String no;
    private String description;
    private LocalDate edition;
    private Integer revision;
    private DrawingType type;

    @JsonIgnore
    @ManyToOne
    private Project project;

    public Sheet() {
    }

    public Sheet(String no, String description, LocalDate edition, Integer revision, DrawingType type, Project project) {
        this.no = no;
        this.description = description;
        this.edition = edition;
        this.revision = revision;
        this.type = type;
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEdition() {
        return edition;
    }

    public void setEdition(LocalDate edition) {
        this.edition = edition;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public DrawingType getType() {
        return type;
    }

    public void setType(DrawingType type) {
        this.type = type;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sheet sheet = (Sheet) o;
        return Objects.equals(id, sheet.id) &&
                Objects.equals(no, sheet.no) &&
                Objects.equals(description, sheet.description) &&
                Objects.equals(edition, sheet.edition) &&
                Objects.equals(revision, sheet.revision) &&
                type == sheet.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, no, description, edition, revision, type);
    }
}
