package com.manager.service;

import com.manager.model.Project;
import com.manager.model.Sheet;
import com.manager.model.Users;
import com.manager.repository.SheetRepository;
import com.manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SheetService {

    private final UserRepository userRepository;
    private final SheetRepository sheetRepository;

    public SheetService(UserRepository userRepository, SheetRepository sheetRepository) {
        this.userRepository = userRepository;
        this.sheetRepository = sheetRepository;
    }


    public Sheet addSheet(String email, String projectId, Sheet sheet) {
        Users user = userRepository.findByEmail(email);
        Project userProject = getUserProject(user.getId().toString(), projectId);
        sheet.setProject(userProject);
        return sheetRepository.save(sheet);
    }

    public List<Sheet> findAllSheets(String email, String projectId) {
        Users user = userRepository.findByEmail(email);
        return getUserProject(user.getId().toString(), projectId).getSheets();
    }

    public Sheet updateSheet(String email, String projectId, Sheet sheet) {
        Users user = userRepository.findByEmail(email);
        Sheet foundSheet = getSheet(user.getId().toString(), projectId, sheet.getId().toString());
        foundSheet.setNo(sheet.getNo());
        foundSheet.setDescription(sheet.getDescription());
        foundSheet.setEdition(sheet.getEdition());
        foundSheet.setRevision(checkRevision(sheet.getRevision()));
        foundSheet.setType(sheet.getType());
        return sheetRepository.save(foundSheet);
    }

    public void deleteSheet(String email, String projectId, String sheetId) {
        Users user = userRepository.findByEmail(email);
        sheetRepository.delete(getSheet(user.getId().toString(), projectId, sheetId));
    }

    private Integer checkRevision(Integer revision) {
        try {
            if(revision < 0) {
                return 0;
            } else {
                return revision;
            }
        } catch (Exception e) {
            revision = 0;
        }
        return revision;
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

    private Sheet getSheet(String userId, String projectId, String sheetId) {
        return userRepository.findById(Integer.parseInt(userId))
                .orElseThrow()
                .getProjects().stream()
                .filter(project -> project.getId().equals(Integer.parseInt(projectId)))
                .findFirst()
                .orElseThrow()
                .getSheets().stream()
                .filter(sht -> sht.getId().equals(Integer.parseInt(sheetId)))
                .findFirst()
                .orElseThrow();
    }

}
