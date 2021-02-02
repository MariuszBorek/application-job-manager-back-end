package com.manager.controller;

import com.manager.model.Sheet;
import com.manager.service.SheetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/projects/sheets")
@CrossOrigin("*")
public class SheetController {

    private final SheetService sheetService;

    public SheetController(SheetService sheetService) {
        this.sheetService = sheetService;
    }

    @GetMapping("/{userId}/{projectId}")
    public List<Sheet> findAllSheets(@PathVariable("userId") final String userId,
                                     @PathVariable("projectId") final String projectId) {
        return sheetService.findAllSheets(userId, projectId);
    }

    @PostMapping("/{userId}/{projectId}")
    public Sheet addSheetToProject(@PathVariable("userId") final String userId,
                                   @PathVariable("projectId") final String projectId,
                                   @RequestBody final Sheet sheet) {
        return sheetService.addSheet(userId, projectId, sheet);
    }

    @PutMapping("/{userId}/{projectId}")
    public Sheet updateTask(@PathVariable("userId") final String userId,
                            @PathVariable("projectId") final String projectId,
                            @RequestBody final Sheet sheet) {
        return sheetService.updateSheet(userId, projectId, sheet);
    }

    @DeleteMapping("/{userId}/{projectId}/{sheetId}")
    public void deleteSheet(@PathVariable("userId") final String userId,
                            @PathVariable("projectId") final String projectId,
                            @PathVariable("sheetId") final String sheetId) {
        sheetService.deleteSheet(userId, projectId, sheetId);
    }

}
