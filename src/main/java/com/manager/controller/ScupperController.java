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

    @PostMapping("/user/{email}/{projectId}")
    public Scupper addScupper(@PathVariable("email") final String email,
                              @PathVariable("projectId") final String projectId,
                              @RequestBody final Scupper scupper) {
        return scupperService.addScupper(email, projectId, scupper);
    }

    @GetMapping("/user/{email}/{projectId}")
    public List<Scupper> findAll(@PathVariable("email") final String email,
                                 @PathVariable("projectId") final String projectId) {
        return scupperService.getScuppers(email, projectId);
    }

    @GetMapping("/user/find-by-project-name/{email}/{projectId}/{phrase}")
    public List<Scupper> findByProjectName(@PathVariable("email") final String email,
                                           @PathVariable("projectId") final String projectId,
                                           @PathVariable("phrase") final String phrase) {
        return scupperService.getScuppersByProjectName(email, projectId, phrase);
    }

    @DeleteMapping("/user/{email}/{projectId}/{scupperId}")
    public List<Scupper> deleteScupper(@PathVariable("email") final String email,
                                       @PathVariable("projectId") final String projectId,
                                       @PathVariable("scupperId") final String scupperId) {
        return scupperService.deleteScupper(email, projectId, scupperId);
    }

    @DeleteMapping("/user/clear-saved-scuppers/{email}/{projectId}")
    public List<Scupper> clearAllSavedScuppers(@PathVariable("email") final String email,
                                               @PathVariable("projectId") final String projectId) {
        return scupperService.clearAllSavedScuppers(email, projectId);
    }
}