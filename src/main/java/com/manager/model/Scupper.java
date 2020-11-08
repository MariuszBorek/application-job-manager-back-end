package com.manager.model;

import java.util.Objects;

public class Scupper {

    private Integer id;
    private String projectName;
    private Double roofArea;
    private Double scupperSideX;
    private Double scupperSideY;
    private Double realScupperArea;
    private Double activeScupperArea;
    private Double waterLevel;
    private Double bottomScupperLevelOverRoof;
    private Double numberOfScuppers;
    private Double numberOfScuppersRound;

    public Scupper() {
    }

    public Integer getId() {
        return id;
    }

    public Scupper setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public Scupper setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public Double getRoofArea() {
        return roofArea;
    }

    public Scupper setRoofArea(Double roofArea) {
        this.roofArea = roofArea;
        return this;
    }

    public Double getScupperSideX() {
        return scupperSideX;
    }

    public Scupper setScupperSideX(Double scupperSideX) {
        this.scupperSideX = scupperSideX;
        return this;
    }

    public Double getScupperSideY() {
        return scupperSideY;
    }

    public Scupper setScupperSideY(Double scupperSideY) {
        this.scupperSideY = scupperSideY;
        return this;
    }

    public Double getRealScupperArea() {
        return realScupperArea;
    }

    public Scupper setRealScupperArea(Double realScupperArea) {
        this.realScupperArea = realScupperArea;
        return this;
    }

    public Double getActiveScupperArea() {
        return activeScupperArea;
    }

    public Scupper setActiveScupperArea(Double activeScupperArea) {
        this.activeScupperArea = activeScupperArea;
        return this;
    }

    public Double getWaterLevel() {
        return waterLevel;
    }

    public Scupper setWaterLevel(Double waterLevel) {
        this.waterLevel = waterLevel;
        return this;
    }

    public Double getBottomScupperLevelOverRoof() {
        return bottomScupperLevelOverRoof;
    }

    public Scupper setBottomScupperLevelOverRoof(Double bottomScupperLevelOverRoof) {
        this.bottomScupperLevelOverRoof = bottomScupperLevelOverRoof;
        return this;
    }

    public Double getNumberOfScuppers() {
        return numberOfScuppers;
    }

    public Scupper setNumberOfScuppers(Double numberOfScuppers) {
        this.numberOfScuppers = numberOfScuppers;
        return this;
    }

    public Double getNumberOfScuppersRound() {
        return numberOfScuppersRound;
    }

    public Scupper setNumberOfScuppersRound(Double numberOfScuppersRound) {
        this.numberOfScuppersRound = numberOfScuppersRound;
        return this;
    }

    public Scupper build() {
        Scupper scupper = new Scupper();
        scupper.id = this.id;
        scupper.projectName = this.projectName;
        scupper.roofArea = this.roofArea;
        scupper.scupperSideX = this.scupperSideX;
        scupper.scupperSideY = this.scupperSideY;
        scupper.realScupperArea = this.realScupperArea;
        scupper.activeScupperArea = this.activeScupperArea;
        scupper.waterLevel = this.waterLevel;
        scupper.bottomScupperLevelOverRoof = this.bottomScupperLevelOverRoof;
        scupper.numberOfScuppers = this.numberOfScuppers;
        scupper.numberOfScuppersRound = this.numberOfScuppersRound;
        return scupper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scupper scupper = (Scupper) o;
        return Objects.equals(id, scupper.id) &&
                Objects.equals(projectName, scupper.projectName) &&
                Objects.equals(roofArea, scupper.roofArea) &&
                Objects.equals(scupperSideX, scupper.scupperSideX) &&
                Objects.equals(scupperSideY, scupper.scupperSideY) &&
                Objects.equals(realScupperArea, scupper.realScupperArea) &&
                Objects.equals(activeScupperArea, scupper.activeScupperArea) &&
                Objects.equals(waterLevel, scupper.waterLevel) &&
                Objects.equals(bottomScupperLevelOverRoof, scupper.bottomScupperLevelOverRoof) &&
                Objects.equals(numberOfScuppers, scupper.numberOfScuppers) &&
                Objects.equals(numberOfScuppersRound, scupper.numberOfScuppersRound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectName, roofArea, scupperSideX, scupperSideY, realScupperArea, activeScupperArea, waterLevel, bottomScupperLevelOverRoof, numberOfScuppers, numberOfScuppersRound);
    }
}
