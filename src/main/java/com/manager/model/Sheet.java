package com.manager.model;

import com.manager.enums.DrawingType;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
public class Sheet {

    private Integer id;
    private String no;
    private String description;
    private LocalDate edition;
    private Integer revision;
    private DrawingType type;

    public Sheet(Integer id,
                 String no,
                 String description,
                 LocalDate edition,
                 Integer revision,
                 DrawingType type) {
        this.id = id;
        this.no = no;
        this.description = description;
        this.edition = edition;
        this.revision = revision;
        this.type = type;
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
