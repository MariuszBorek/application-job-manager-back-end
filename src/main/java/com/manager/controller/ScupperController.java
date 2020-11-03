package com.manager.controller;

import com.manager.service.ScupperService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tools/scuppers")
@CrossOrigin("*")
public class ScupperController {

    private ScupperService scupperService;

    public ScupperController(ScupperService scupperService) {
        this.scupperService = scupperService;
    }

    @GetMapping
    public Double countScuppers(@RequestParam final String roofArea,
                                @RequestParam final String scupperSideX,
                                @RequestParam final String scupperSideY,
                                @RequestParam final String bottomScupperLevelOverRoof,
                                @RequestParam final String waterLevel) {
        return scupperService.countScuppers(roofArea,
                scupperSideX,
                scupperSideY,
                bottomScupperLevelOverRoof,
                waterLevel);
    }
}
