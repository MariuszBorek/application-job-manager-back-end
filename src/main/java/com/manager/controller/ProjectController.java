package com.manager.controller;


import com.manager.model.Project;
import com.manager.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/projects")
@CrossOrigin("http://localhost:4200")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/{id}")
    public Project addProject(@PathVariable("id") final String id,
                              @RequestBody final Project project) {
        return projectService.addProject(id, project);
    }

    @GetMapping("/{id}")
    public List<Project> getUser(@PathVariable("id") final String id) {
        return projectService.getUserProjects(id);
    }

    @DeleteMapping("/{projectId}")
    public List<Project> deleteProject(@PathVariable("projectId") final String projectId) {
        return projectService.deleteProject(projectId);
    }

}
