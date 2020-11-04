package com.manager.service;

import com.manager.model.Scupper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScupperService {

    private final Map<Integer, Scupper> scuppers = new HashMap<>();
    private Integer id = 0;

    public void autoIncrement() {
        id = id + 1;
    }

    public Scupper countScuppers(String roofArea,
                                 String scupperSideX,
                                 String scupperSideY,
                                 String bottomScupperLevelOverRoof,
                                 String waterLevel) {
        Double roofAreaNo = checkIfDoubleCorrectAndConvert(roofArea);
        Double scupperSideXNo = checkIfDoubleCorrectAndConvert(scupperSideX);
        Double scupperSideYNo = checkIfDoubleCorrectAndConvert(scupperSideY);
        Double bottomScupperLevelOverRoofNo = checkIfDoubleCorrectAndConvert(bottomScupperLevelOverRoof);
        Double waterLevelNo = checkIfDoubleCorrectAndConvert(waterLevel);

        double numberOfScuppers = 0;
        double activeScupperArea = 0;
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
            numberOfScuppers = 0;
        }
        return new Scupper()
                .setRoofArea(roofAreaNo)
                .setScupperSideX(scupperSideXNo)
                .setScupperSideY(scupperSideYNo)
                .setRealScupperArea(realScupperArea)
                .setActiveScupperArea(activeScupperArea)
                .setWaterLevel(waterLevelNo)
                .setBottomScupperLevelOverRoof(bottomScupperLevelOverRoofNo)
                .setNumberOfScuppers(numberOfScuppers)
                .setNumberOfScuppersRound(Math.ceil(numberOfScuppers))
                .build();
    }

    private Double checkIfDoubleCorrectAndConvert(String number) {
        return Double.parseDouble(number);
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

}
