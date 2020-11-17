package com.manager.service;

import com.manager.configuration.SampleUsersConfig;
import com.manager.enums.DrawingType;
import com.manager.model.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Profile("dev")
public class InitService {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final SheetService sheetService;
    private final NoteService noteService;

    private final SampleUsersConfig sampleUsersConfig;

    public InitService(UserService userService, ProjectService projectService, TaskService taskService, SheetService sheetService, NoteService noteService, SampleUsersConfig sampleUsersConfig) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
        this.sheetService = sheetService;
        this.noteService = noteService;
        this.sampleUsersConfig = sampleUsersConfig;
        init();
    }

    private void init() {
        for (int i = 0; i < sampleUsersConfig.getNumberOfUsers(); i++) {
            User user = new User("User_" + i, "Surname_" + i, i + "_" + sampleUsersConfig.getMailPrefix(), "qw");
            userService.addUser(user);
            for (int j = 0; j < sampleUsersConfig.getNumberOfProjects(); j++) {
                Project project = new Project("Project " + j, "project description " + j, user);
                projectService.addProject(user.getId().toString(), project);
                for (int k = 0; k < sampleUsersConfig.getNumberOfTasks(); k++) {
                    Task task = new Task("Topic " + k, "text " + k, LocalDate.now(), false, false,project);
                    taskService.addTask(user.getId().toString(), project.getId().toString(), task);
                }
                for (int k = 0; k < sampleUsersConfig.getNumberOfSheets(); k++) {
                    Sheet sheet = new Sheet("ARCH-B-10-" + k, "plan of the building " + k, LocalDate.now(), k, DrawingType.PLAN, project);
                    sheetService.addSheet(user.getId().toString(), project.getId().toString(), sheet);
                }
                for (int k = 0; k < sampleUsersConfig.getNumberOfSheets(); k++) {
                    Note note = new Note("text of the note " + k);
                    noteService.addNote(user.getId().toString(), project.getId().toString(), note);
                }
            }
        }
    }
}
