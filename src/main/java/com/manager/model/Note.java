package com.manager.model;

import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public class Note {
    private Integer id;
    private String text;

    public Note(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return Objects.equals(id, note1.id) &&
                Objects.equals(text, note1.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
