package com.manager.service;

import com.manager.model.Scupper;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ScupperService {

    private final Map<Integer, Scupper> scuppers = new HashMap<>();
    private Integer id = 0;

    public void autoIncrement() {
        id = id + 1;
    }

    public Scupper countScuppers(String projectName,
                                 String roofArea,
                                 String scupperSideX,
                                 String scupperSideY,
                                 String bottomScupperLevelOverRoof,
                                 String waterLevel) {

        Double roofAreaNo = checkIfDoubleCorrectAndConvert(roofArea);
        Double scupperSideXNo = checkIfDoubleCorrectAndConvert(scupperSideX);
        Double scupperSideYNo = checkIfDoubleCorrectAndConvert(scupperSideY);
        Double bottomScupperLevelOverRoofNo = checkBottomScupperLevelOverRoofOrReturnDefaultValue(bottomScupperLevelOverRoof);
        Double waterLevelNo = checkWaterLevelOrReturnDefaultValue(waterLevel);

        double numberOfScuppers = 0.0;
        double activeScupperArea = 0.0;

        double realScupperArea = scupperSideXNo * scupperSideYNo;
        double allScuppersArea = roofAreaNo * 0.03 * 0.8 * 25;
        double activeScupperSideYNo = waterLevelNo - bottomScupperLevelOverRoofNo;
        double topScupperLevelOverRoof = bottomScupperLevelOverRoofNo + scupperSideYNo;

        if (waterLevelNo <= topScupperLevelOverRoof && waterLevelNo > bottomScupperLevelOverRoofNo) {
            activeScupperArea = scupperSideXNo * activeScupperSideYNo;
            numberOfScuppers = allScuppersArea / activeScupperArea;
        } else if (waterLevelNo > topScupperLevelOverRoof) {
            activeScupperArea = scupperSideXNo * scupperSideYNo;
            numberOfScuppers = allScuppersArea / activeScupperArea;
        } else {
            numberOfScuppers = 0.0;
        }

        return new Scupper()
                .setProjectName(projectName.trim())
                .setRoofArea(roofAreaNo)
                .setScupperSideX(scupperSideXNo)
                .setScupperSideY(scupperSideYNo)
                .setRealScupperArea(realScupperArea)
                .setActiveScupperArea(activeScupperArea)
                .setWaterLevel(waterLevelNo)
                .setBottomScupperLevelOverRoof(bottomScupperLevelOverRoofNo)
                .setNumberOfScuppers(RoundToTwoDecimalPlaces(numberOfScuppers))
                .setNumberOfScuppersRound(ceilNumberOfScuppers(numberOfScuppers))
                .build();
    }

    private Double ceilNumberOfScuppers(Double numberOfScuppers) {
        double ceilNumberOfScuppers = Math.ceil(numberOfScuppers);
        if(ceilNumberOfScuppers == 1) {
            return 2.0;
        }
        return ceilNumberOfScuppers;
    }

    private Double RoundToTwoDecimalPlaces(Double numberOfScuppers) {
        if(!(numberOfScuppers <= 0.0 || numberOfScuppers.isInfinite() || numberOfScuppers.isNaN())) {
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            return Double.valueOf(decimalFormat.format(numberOfScuppers));
        } else {
            return 0d;
        }

    }

    private Double checkIfDoubleCorrectAndConvert(String number) {
        if(number.isEmpty() || number.isBlank() || number.matches("\\D*")){
            return 0.0;
        }
            double convertedNumber = Double.parseDouble(number);
            return Math.max(convertedNumber, 0.0);
    }

    private Double checkBottomScupperLevelOverRoofOrReturnDefaultValue(String bottomScupperLevelOverRoof) {
        if(bottomScupperLevelOverRoof.isEmpty() || bottomScupperLevelOverRoof.isBlank() || bottomScupperLevelOverRoof.matches("\\D*")) {
            return 5.0;
        }
        double convertedBottomScupperLevelOverRoof = Double.parseDouble(bottomScupperLevelOverRoof);
        return Math.max(convertedBottomScupperLevelOverRoof, 0.0);
    }

    private Double checkWaterLevelOrReturnDefaultValue(String waterLevel) {
        if(waterLevel.isEmpty() || waterLevel.isBlank()  || waterLevel.matches("\\D*")) {
            return 10.0;
        }
        double convertedWaterLevel = Double.parseDouble(waterLevel);
        return Math.max(convertedWaterLevel, 0.0);
    }

    public Scupper addScupper(Scupper scupper) {
        autoIncrement();
        scupper.setId(id);
        scuppers.put(id, scupper);
        return scupper;
    }

    public List<Scupper> getScuppers() {
        return new ArrayList<>(scuppers.values());
    }

    public List<Scupper> getScuppersByProjectName(String phrase) {
        return scuppers.values().stream()
                .filter(e -> e.getProjectName().toLowerCase().matches(".*" + phrase.toLowerCase() + ".*"))
                .collect(Collectors.toList());
    }

    public List<Scupper> clearAllSavedScuppers() {
        scuppers.clear();
        return new ArrayList<>(scuppers.values());
    }

    public List<Scupper> deleteScupper(String id) {
        scuppers.remove(Integer.valueOf(id));
        return new ArrayList<>(scuppers.values());
    }
}