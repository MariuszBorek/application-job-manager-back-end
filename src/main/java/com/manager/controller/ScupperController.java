package com.manager.controller;

import com.manager.model.Scupper;
import com.manager.service.ScupperService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tools/scuppers")
@CrossOrigin("*")
public class ScupperController {

    private final ScupperService scupperService;

    public ScupperController(ScupperService scupperService) {
        this.scupperService = scupperService;
    }

    @GetMapping("/check")
    public Scupper countScuppers(@RequestParam final String projectName,
                                 @RequestParam final String roofArea,
                                 @RequestParam final String scupperSideX,
                                 @RequestParam final String scupperSideY,
                                 @RequestParam final String bottomScupperLevelOverRoof,
                                 @RequestParam final String waterLevel) {
        return scupperService.countScuppers(projectName,
                roofArea,
                scupperSideX,
                scupperSideY,
                bottomScupperLevelOverRoof,
                waterLevel);
    }

    @PostMapping("/user/{userId}/{projectId}")
    public Scupper addScupper(@PathVariable("userId") final String userId,
                              @PathVariable("projectId") final String projectId,
                              @RequestBody final Scupper scupper) {
        return scupperService.addScupper(userId, projectId, scupper);
    }

    @GetMapping("/user/{userId}/{projectId}")
    public List<Scupper> findAll(@PathVariable("userId") final String userId,
                                 @PathVariable("projectId") final String projectId) {
        return scupperService.getScuppers(userId, projectId);
    }

    @GetMapping("/user/find-by-project-name/{userId}/{projectId}/{phrase}")
    public List<Scupper> findByProjectName(@PathVariable("userId") final String userId,
                                           @PathVariable("projectId") final String projectId,
                                           @PathVariable("phrase") final String phrase) {
        return scupperService.getScuppersByProjectName(userId, projectId, phrase);
    }

    @DeleteMapping("/user/{userId}/{projectId}/{scupperId}")
    public List<Scupper> deleteScupper(@PathVariable("userId") final String userId,
                                       @PathVariable("projectId") final String projectId,
                                       @PathVariable("scupperId") final String scupperId) {
        return scupperService.deleteScupper(userId, projectId, scupperId);
    }

    @DeleteMapping("/user/clear-saved-scuppers/{userId}/{projectId}")
    public List<Scupper> clearAllSavedScuppers(@PathVariable("userId") final String userId,
                                               @PathVariable("projectId") final String projectId) {
        return scupperService.clearAllSavedScuppers(userId, projectId);
    }
}