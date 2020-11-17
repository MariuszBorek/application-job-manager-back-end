package com.manager.service;

import com.manager.model.Project;
import com.manager.model.User;
import com.manager.repository.ProjectRepository;
import com.manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public ProjectService(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public Project addProject(String id, Project project) {
        User foundUser = userRepository.findById(Integer.parseInt(id)).orElseThrow();
        projectRepository.save(project);
        project.setUser(foundUser);
        userRepository.save(foundUser);
        return project;
    }


    public List<Project> getUserProjects(String id) {
        return userRepository.findAll().stream()
                .filter(user -> user.getId().equals(Integer.parseInt(id)))
                .findFirst()
                .orElseThrow()
                .getProjects();
    }

    // todo - delete function

}
