package com.manager.service;

import com.manager.model.Note;
import com.manager.model.Project;
import com.manager.model.Users;
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

    public Note addNote(String email, String projectId, Note note) {
        Project userProject = getUserProject(email, projectId);
        note.setProject(userProject);
        return noteRepository.save(note);
    }

    public List<Note> findAllNotes(String email, String projectId) {
        return getUserProject(email, projectId).getNotes();

    }

    public Note updateNote(String email, String projectId, Note note) {
        Users user = userRepository.findByEmail(email);
        Note foundNote = getNote(user.getId().toString(), projectId, note.getId().toString());
        foundNote.setText(note.getText());
        return noteRepository.save(foundNote);
    }

        public void deleteNote(String email, String projectId, String noteId) {
            Users user = userRepository.findByEmail(email);
            noteRepository.delete(getNote(user.getId().toString(), projectId, noteId));
    }

    private Project getUserProject(String email, String projectId) {
        return userRepository.findByEmail(email)
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
