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
    private final ScupperService scupperService;

    private final SampleUsersConfig sampleUsersConfig;

    public InitService(UserService userService,
                       ProjectService projectService,
                       TaskService taskService,
                       SheetService sheetService,
                       NoteService noteService,
                       SampleUsersConfig sampleUsersConfig,
                       ScupperService scupperService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
        this.sheetService = sheetService;
        this.noteService = noteService;
        this.sampleUsersConfig = sampleUsersConfig;
        this.scupperService = scupperService;
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
                    Task task = new Task()
                            .setTopic("Topic " + k)
                            .setText("text " + k)
                            .setDate(LocalDate.now())
                            .setPriority(false)
                            .setExecution(false)
                            .setProject(project)
                            .build();
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
                for (int k = 0; k < sampleUsersConfig.getNumberOfScuppers(); k++) {
                    Scupper scupper = new Scupper()
                            .setProjectName("Sample building " + k)
                            .setRoofArea(1123d)
                            .setScupperSideX(70d)
                            .setScupperSideY(10d)
                            .setRealScupperArea(700d)
                            .setWaterLevel(10d)
                            .setBottomScupperLevelOverRoof(5d)
                            .setNumberOfScuppers(3.64)
                            .setNumberOfScuppersRound(4d)
                            .build();
                    scupperService.addScupper(user.getId().toString(), project.getId().toString(), scupper);
                }
            }
        }
    }
}
