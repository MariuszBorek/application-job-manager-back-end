package com.manager.service;

import com.manager.model.Project;
import com.manager.model.Scupper;
import com.manager.model.User;
import com.manager.repository.ScupperRepository;
import com.manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScupperService {

    private final UserRepository userRepository;
    private final ScupperRepository scupperRepository;


    public ScupperService(UserRepository userRepository, ScupperRepository scupperRepository) {
        this.userRepository = userRepository;
        this.scupperRepository = scupperRepository;
    }

    public Scupper countScuppers(String projectName,
                                 String roofArea,
                                 String scupperSideX,
                                 String scupperSideY,
                                 String bottomScupperLevelOverRoof,
                                 String waterLevel) {

        Double roofAreaNo = checkIfDoubleCorrectAndConvert(roofArea);
        Double scupperSideXNo = checkIfScupperSidesAreCorrectAndConvert(scupperSideX);
        Double scupperSideYNo = checkIfScupperSidesAreCorrectAndConvert(scupperSideY);
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
                .setNumberOfScuppers(roundToTwoDecimalPlaces(numberOfScuppers))
                .setNumberOfScuppersRound(ceilNumberOfScuppers(numberOfScuppers))
                .build();
    }

    private Double ceilNumberOfScuppers(Double numberOfScuppers) {
        if(numberOfScuppers == null) {
            throw new IllegalArgumentException();
        }
        double ceilNumberOfScuppers = Math.ceil(numberOfScuppers);
        if(ceilNumberOfScuppers == 1) {
            return 2.0;
        }
        return ceilNumberOfScuppers;
    }

    private Double roundToTwoDecimalPlaces(Double numberOfScuppers) {
        if(numberOfScuppers == null) {
            throw new IllegalArgumentException();
        }
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

    private Double checkIfScupperSidesAreCorrectAndConvert(String number) {
        if(number.isEmpty() || number.isBlank() || number.matches("\\D*")){
            return 10.0;
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

    public Scupper addScupper(String userId, String projectId, Scupper scupper) {
        Project userProject = getUserProject(userId, projectId);
        scupper.setProject(userProject);
        return scupperRepository.save(scupper);
    }

    public List<Scupper> getScuppers(String userId, String projectId) {
        return getUserProject(userId, projectId).getScuppers();
    }

    public List<Scupper> getScuppersByProjectName(String userId, String projectId, String phrase) {
        return getUserProject(userId, projectId).getScuppers().stream()
                .filter(e -> e.getProjectName().toLowerCase().matches(".*" + phrase.toLowerCase() + ".*"))
                .collect(Collectors.toList());
    }

    public List<Scupper> clearAllSavedScuppers(String userId, String projectId) {
        List<Scupper> scuppers = getUserProject(userId, projectId).getScuppers();
        scupperRepository.deleteAll(scuppers);
        return scuppers;
    }

    public List<Scupper> deleteScupper(String userId, String projectId, String scupperId) {
        Scupper scupper = getScupper(userId, projectId, scupperId);
        scupperRepository.delete(scupper);
        return getUserProject(userId, projectId).getScuppers();
    }

    public Project getUserProject(String userId, String projectId) {
        return userRepository.findById(Integer.parseInt(userId))
                .orElseThrow()
                .getProjects()
                .stream()
                .filter(project -> project.getId().equals(Integer.parseInt(projectId)))
                .findFirst()
                .orElseThrow();
    }

    private Scupper getScupper(String userId, String projectId, String scupperId) {
        return userRepository.findById(Integer.parseInt(userId))
                .orElseThrow()
                .getProjects().stream()
                .filter(project -> project.getId().equals(Integer.parseInt(projectId)))
                .findFirst()
                .orElseThrow()
                .getScuppers().stream()
                .filter(scpr -> scpr.getId().equals(Integer.parseInt(scupperId)))
                .findFirst()
                .orElseThrow();
    }
}