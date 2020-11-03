package com.manager.service;

import com.manager.model.Scupper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScupperService {

    private final Map<Integer, Scupper> scuppers = new HashMap<>();
    private Integer id = 0;

    public void autoIncrement() {
        id = id + 1;
    }

    public Double countScuppers(String roofArea,
                                String scupperSideX,
                                String scupperSideY,
                                String bottomScupperLevelOverRoof,
                                String waterLevel) {
        Double roofAreaNo = checkIfDoubleCorrectAndConvert(roofArea);
        Double scupperSideXNo = checkIfDoubleCorrectAndConvert(scupperSideX);
        Double scupperSideYNo = checkIfDoubleCorrectAndConvert(scupperSideY);
        Double bottomScupperLevelOverRoofNo = checkIfDoubleCorrectAndConvert(bottomScupperLevelOverRoof);
        Double waterLevelNo = checkIfDoubleCorrectAndConvert(waterLevel);

        double realScupperArea = scupperSideXNo * scupperSideYNo;
        double allScuppersArea = roofAreaNo * 0.03 * 0.8 * 25; // !!!

        double activeScupperArea = 0; // !!!

        double activeScupperSideYNo = waterLevelNo - bottomScupperLevelOverRoofNo;
        double topScupperLevelOverRoof = bottomScupperLevelOverRoofNo + scupperSideYNo;

        if (topScupperLevelOverRoof >= waterLevelNo) {
            activeScupperArea = realScupperArea;
        }

        // TODO: count activeScupper
//        activeScupperArea = activeScupperSideYNo * scupperSideXNo;
//        if (activeScupperSideYNo >= 0.0) {
//            activeScupperArea = activeScupperSideYNo * scupperSideXNo;
//        } else {
//            activeScupperArea = 0.0;
//        }
//        Math.ceil(allScuppersArea / activeScupperArea)
        return allScuppersArea / activeScupperArea;
    }

    private Double checkIfDoubleCorrectAndConvert(String number) {
        // TODO: check if correct
        return Double.parseDouble(number);
    }

//    public Scupper addScupper(Scupper scupper) {
//            autoIncrement();
//            scupper.setId(id);
//            scuppers.put(id, scupper);
//        return scupper;
//    }


}
