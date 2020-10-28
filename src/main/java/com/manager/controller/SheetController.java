package com.manager.controller;

import com.manager.model.Sheet;
import com.manager.service.SheetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sheets")
@CrossOrigin("*")
public class SheetController {

    private final SheetService sheetService;

    public SheetController(SheetService sheetService) {
        this.sheetService = sheetService;
    }

    @GetMapping
    public List<Sheet> findAllSheets() {
        return sheetService.findAllSheet();
    }

    @PostMapping
    public Sheet addSheet(@RequestBody final Sheet sheet) {
        return sheetService.addSheet(sheet);
    }

    @PutMapping("/update")
    public Sheet updateSheet(@RequestBody final Sheet sheet) {
        return sheetService.updateSheet(sheet);
    }

    @DeleteMapping("/{id}")
    public Sheet deleteSheet(@PathVariable final Integer id) {
        return sheetService.deleteSheet(id);
    }

}
