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

    @GetMapping("/{email}/{projectId}")
    public List<Sheet> findAllSheets(@PathVariable("email") final String email,
                                     @PathVariable("projectId") final String projectId) {
        return sheetService.findAllSheets(email, projectId);
    }

    @PostMapping("/{email}/{projectId}")
    public Sheet addSheetToProject(@PathVariable("email") final String email,
                                   @PathVariable("projectId") final String projectId,
                                   @RequestBody final Sheet sheet) {
        return sheetService.addSheet(email, projectId, sheet);
    }

    @PutMapping("/{email}/{projectId}")
    public Sheet updateTask(@PathVariable("email") final String email,
                            @PathVariable("projectId") final String projectId,
                            @RequestBody final Sheet sheet) {
        return sheetService.updateSheet(email, projectId, sheet);
    }

    @DeleteMapping("/{email}/{projectId}/{sheetId}")
    public void deleteSheet(@PathVariable("email") final String email,
                            @PathVariable("projectId") final String projectId,
                            @PathVariable("sheetId") final String sheetId) {
        sheetService.deleteSheet(email, projectId, sheetId);
    }

}
