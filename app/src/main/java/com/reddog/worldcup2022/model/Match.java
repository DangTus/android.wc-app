package com.reddog.worldcup2022.model;

import java.util.List;

public class Match {
    private Team awayT=null;
    private Team homeT=null;
    private List<Goal> awatG=null;
    private List<Goal> homeG=null;
    private String date=null;
    private String time=null;
    private String stage=null;
    private String nameStage=null;
    private int awayScore=0;
    private int homeScore=0;
    private int awayScorePen=0;
    private int homeScorePen=0;

    public Match() {
    }

    public Team getAwayT() {
        return awayT;
    }

    public void setAwayT(Team awayT) {
        this.awayT = awayT;
    }

    public Team getHomeT() {
        return homeT;
    }

    public void setHomeT(Team homeT) {
        this.homeT = homeT;
    }

    public List<Goal> getAwatG() {
        return awatG;
    }

    public void setAwatG(List<Goal> awatG) {
        this.awatG = awatG;
    }

    public List<Goal> getHomeG() {
        return homeG;
    }

    public void setHomeG(List<Goal> homeG) {
        this.homeG = homeG;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getNameStage() {
        return nameStage;
    }

    public void setNameStage(String nameStage) {
        this.nameStage = nameStage;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScorePen() {
        return awayScorePen;
    }

    public void setAwayScorePen(int awayScorePen) {
        this.awayScorePen = awayScorePen;
    }

    public int getHomeScorePen() {
        return homeScorePen;
    }

    public void setHomeScorePen(int homeScorePen) {
        this.homeScorePen = homeScorePen;
    }

    public Match(Team awayT, Team homeT, List<Goal> awatG, List<Goal> homeG, String date, String time, String stage, String nameStage, int awayScore, int homeScore, int awayScorePen, int homeScorePen) {
        this.awayT = awayT;
        this.homeT = homeT;
        this.awatG = awatG;
        this.homeG = homeG;
        this.date = date;
        this.time = time;
        this.stage = stage;
        this.nameStage = nameStage;
        this.awayScore = awayScore;
        this.homeScore = homeScore;
        this.awayScorePen = awayScorePen;
        this.homeScorePen = homeScorePen;
    }

    public void setScore(String score){
        this.homeScore= Integer.valueOf(score.split("-")[0]);
        this.awayScore= Integer.valueOf(score.split("-")[1]);
    }
    public void setScorePen(String scorePen){
        this.homeScorePen= Integer.valueOf(scorePen.split("-")[0]);
        this.awayScorePen= Integer.valueOf(scorePen.split("-")[1]);
    }
    public String getNameStageString(String nameStage){
        return nameStage.replace("_"," ");
    }
    public String getStageString(String stage){
        return stage.replace("_"," ");
    }
}
