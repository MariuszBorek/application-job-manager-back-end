package com.manager.service;

import com.manager.enums.DrawingType;
import com.manager.model.Sheet;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SheetService {

    private final Map<Integer, Sheet> sheets = new HashMap<>();
    private Integer id = 0;

    public void autoIncrement() {
        id = id + 1;
    }

    public List<Sheet> findAllSheet() {
        return new ArrayList<>(sheets.values());
    }

    public Sheet addSheet(Sheet sheet) {
        autoIncrement();
        sheet.setId(id);
        sheets.put(id, sheet);
        return sheet;
    }

    public Sheet deleteSheet(Integer id) {
        Sheet sheet = sheets.get(id);
        sheets.remove(sheet.getId());
        return sheet;
    }

    public Sheet updateSheet(Sheet sheet) {
        Sheet foundSheet = sheets.get(sheet.getId());
        foundSheet.setNo(sheet.getNo());
        foundSheet.setDescription(sheet.getDescription());
        foundSheet.setEdition(sheet.getEdition());
        foundSheet.setRevision(checkRevision(sheet.getRevision()));
        foundSheet.setType(DrawingType.valueOf(sheet.getType().toString()));
        return foundSheet;
    }

    public Integer checkRevision(Integer revision) {
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

}
