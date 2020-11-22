package com.manager.controller;

import com.manager.model.Note;
import com.manager.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/projects/notes")
@CrossOrigin("http://localhost:4200")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{userId}/{projectId}")
    public List<Note> findAllNotes(@PathVariable("userId") final String userId,
                                   @PathVariable("projectId") final String projectId) {
        return noteService.findAllNotes(userId, projectId);
    }

    @PostMapping("/{userId}/{projectId}")
    public Note addNoteToProject(@PathVariable("userId") final String userId,
                                 @PathVariable("projectId") final String projectId,
                                 @RequestBody final Note note) {
        return noteService.addNote(userId, projectId, note);
    }

    @PutMapping("/{userId}/{projectId}")
    public Note updateNote(@PathVariable("userId") final String userId,
                           @PathVariable("projectId") final String projectId,
                           @RequestBody final Note note) {
        return noteService.updateNote(userId, projectId, note);
    }

    @DeleteMapping("/{userId}/{projectId}/{notesId}")
    public void deleteNote(@PathVariable("userId") final String userId,
                           @PathVariable("projectId") final String projectId,
                           @PathVariable("notesId") final String notesId) {
        noteService.deleteNote(userId, projectId, notesId);
    }

}
