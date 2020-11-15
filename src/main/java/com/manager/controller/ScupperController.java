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

    @GetMapping
    public List<Scupper> findAll() {
        return scupperService.getScuppers();
    }

    @GetMapping("/find-by-project-name/{phrase}")
    public List<Scupper> findByProjectName(@PathVariable("phrase") final String phrase) {
        return scupperService.getScuppersByProjectName(phrase);
    }

    @DeleteMapping("/clear-saved-scuppers")
    public List<Scupper> clearAllSavedScuppers() {
        return scupperService.clearAllSavedScuppers();
    }

    @DeleteMapping("/{id}")
    public List<Scupper> deleteScupper(@PathVariable("id") final String  id) {
        return scupperService.deleteScupper(id);
    }


    @PostMapping
    public Scupper addScupper(@RequestBody final Scupper scupper) {
        return scupperService.addScupper(scupper);
    }

}