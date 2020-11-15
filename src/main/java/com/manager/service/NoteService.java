package com.manager.service;

import com.manager.model.Note;
import com.manager.model.Project;
import com.manager.repository.NoteRepository;
import com.manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    public NoteService(UserRepository userRepository, NoteRepository noteRepository) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
    }

    public Note addNote(String userId, String projectId, Note note) {
        Project userProject = getUserProject(userId, projectId);
        note.setProject(userProject);
        return noteRepository.save(note);
    }

    public List<Note> findAllNotes(String userId, String projectId) {
        return getUserProject(userId, projectId).getNotes();

    }

    public Note updateNote(String userId, String projectId, Note note) {
        Note foundNote = getNote(userId, projectId, note.getId().toString());
        foundNote.setText(note.getText());
        return noteRepository.save(foundNote);
    }

        public void deleteNote(String userId, String projectId, String noteId) {
        noteRepository.delete(getNote(userId, projectId, noteId));
    }

    private Project getUserProject(String userId, String projectId) {
        return userRepository.findById(Integer.parseInt(userId))
                .orElseThrow()
                .getProjects()
                .stream()
                .filter(project -> project.getId().equals(Integer.parseInt(projectId)))
                .findFirst()
                .orElseThrow();
    }

    private Note getNote(String userId, String projectId, String noteId) {
        return userRepository.findById(Integer.parseInt(userId))
                .orElseThrow()
                .getProjects().stream()
                .filter(project -> project.getId().equals(Integer.parseInt(projectId)))
                .findFirst()
                .orElseThrow()
                .getNotes().stream()
                .filter(nte -> nte.getId().equals(Integer.parseInt(noteId)))
                .findFirst()
                .orElseThrow();
    }

}
