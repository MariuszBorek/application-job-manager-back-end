package com.manager.controller;

import com.manager.model.Note;
import com.manager.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/projects/notes")
@CrossOrigin("*")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{email}/{projectId}")
    public List<Note> findAllNotes(@PathVariable("email") final String email,
                                   @PathVariable("projectId") final String projectId) {
        return noteService.findAllNotes(email, projectId);
    }

    @PostMapping("/{email}/{projectId}")
    public Note addNoteToProject(@PathVariable("email") final String email,
                                 @PathVariable("projectId") final String projectId,
                                 @RequestBody final Note note) {
        return noteService.addNote(email, projectId, note);
    }

    @PutMapping("/{email}/{projectId}")
    public Note updateNote(@PathVariable("email") final String email,
                           @PathVariable("projectId") final String projectId,
                           @RequestBody final Note note) {
        return noteService.updateNote(email, projectId, note);
    }

    @DeleteMapping("/{email}/{projectId}/{notesId}")
    public void deleteNote(@PathVariable("email") final String email,
                           @PathVariable("projectId") final String projectId,
                           @PathVariable("notesId") final String notesId) {
        noteService.deleteNote(email, projectId, notesId);
    }

}
