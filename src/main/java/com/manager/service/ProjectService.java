package com.manager.service;

import com.manager.model.*;
import com.manager.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final TaskArchiveRepository taskArchiveRepository;
    private final ScupperRepository scupperRepository;
    private final NoteRepository noteRepository;
    private final SheetRepository sheetRepository;


    public ProjectService(UserRepository userRepository,
                          ProjectRepository projectRepository,
                          TaskRepository taskRepository,
                          TaskArchiveRepository taskArchiveRepository,
                          ScupperRepository scupperRepository,
                          NoteRepository noteRepository,
                          SheetRepository sheetRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.taskArchiveRepository = taskArchiveRepository;
        this.scupperRepository = scupperRepository;
        this.noteRepository = noteRepository;
        this.sheetRepository = sheetRepository;
    }

    public Project addProject(String id, Project project) {
        User foundUser = userRepository.findById(Integer.parseInt(id)).orElseThrow();
        projectRepository.save(project);
        project.setUser(foundUser);
        userRepository.save(foundUser);
        return project;
    }


    public List<Project> getUserProjects(String userid) {
        return userRepository.findAll().stream()
                .filter(user -> user.getId().equals(Integer.parseInt(userid)))
                .findFirst()
                .orElseThrow()
                .getProjects();
    }

    public List<Project> deleteProject(String projectId) {
        Project project = projectRepository.findById(Integer.parseInt(projectId)).orElseThrow();
        Project foundProject = projectRepository.findById(Integer.parseInt(projectId)).orElseThrow();
        List<Scupper> scuppers = foundProject.getScuppers();
        scupperRepository.deleteAll(scuppers);
        List<TaskArchive> taskArchive = foundProject.getTaskArchive();
        taskArchiveRepository.deleteAll(taskArchive);
        List<Note> notes = foundProject.getNotes();
        noteRepository.deleteAll(notes);
        List<Task> tasks = foundProject.getTasks();
        taskRepository.deleteAll(tasks);
        List<Sheet> sheets = foundProject.getSheets();
        sheetRepository.deleteAll(sheets);
        projectRepository.delete(project);
        return null;
    }
}
