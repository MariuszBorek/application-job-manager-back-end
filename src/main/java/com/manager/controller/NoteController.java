package com.manager.controller;

import com.manager.model.Note;
import com.manager.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin("*")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<Note> findAllNotes() {
        return noteService.findAllNotes();
    }

    @PostMapping
    public Note addNote(@RequestBody final Note note) {
        return noteService.addNote(note);
    }

    @PutMapping("/update")
    public Note updateNote(@RequestBody final Note note) {
        return noteService.updateNote(note);
    }

    @DeleteMapping("/{id}")
    public Note deleteNote(@PathVariable final Integer id) {
        return noteService.deleteNote(id);
    }

}
