package com.manager.controller;


import com.manager.model.Project;
import com.manager.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/projects")
@CrossOrigin("*")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/{email}")
    public Project addProject(@PathVariable("email") final String email,
                              @RequestBody final Project project) {
        return projectService.addProject(email, project);
    }

    @GetMapping("/{email}")
    public List<Project> getUser(@PathVariable("email") final String email) {
        return projectService.getUserProjects(email);
    }

    @DeleteMapping("/{projectId}")
    public List<Project> deleteProject(@PathVariable("projectId") final String projectId) {
        return projectService.deleteProject(projectId);
    }

}
