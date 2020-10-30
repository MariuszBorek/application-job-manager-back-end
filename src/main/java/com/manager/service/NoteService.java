package com.manager.service;

import com.manager.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {
    private final Map<Integer, Note> notes = new HashMap<>();
    private Integer id = 0;

    public void autoIncrement() {
        id = id + 1;
    }

    public List<Note> findAllNotes() {
        return new ArrayList<>(notes.values());
    }

    public Note addNote(Note note) {
        autoIncrement();
        note.setId(id);
        notes.put(id, note);
        return note;
    }

    public Note deleteNote(Integer id) {
        Note note = notes.get(id);
        notes.remove(note.getId());
        return note;
    }

    public Note updateNote(Note note) {
        Note foundNote = notes.get(note.getId());
        foundNote.setText(note.getText());
        return foundNote;
    }















}
