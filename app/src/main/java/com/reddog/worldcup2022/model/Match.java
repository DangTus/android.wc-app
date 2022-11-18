package com.reddog.worldcup2022.model;

import com.reddog.worldcup2022.module.DateModule;

import java.util.Collections;
import java.util.List;

public class Match {

    private Team awayT = null;
    private Team homeT = null;
    private List<Goal> goalList = null;
    private String ngay = null;
    private String time = null;
    private String stage = null;
    private String nameStage = null;
    private int awayScore = 0;
    private int homeScore = 0;
    private int awayScorePen = 0;
    private int homeScorePen = 0;
    private boolean status = false;

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

    public List<Goal> getGoalList() {
        return goalList;
    }

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
    }

    public String getDate() {
        return this.ngay;
    }

    public void setDate(String date) {
       DateModule dateModule = new DateModule(date);
       this.ngay = dateModule.getStringDate();
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status == "null") {
            this.status = false;
        } else {
            this.status = true;
        }
    }

    public void setScore(String score) {
        if(this.status) {
            this.homeScore = Integer.parseInt(score.split("–")[0]);
            this.awayScore = Integer.parseInt(score.split("–")[1]);
        }
    }

    public void setPenScore(String penScore) {
        if (penScore != "null") {
            this.homeScorePen = Integer.parseInt(penScore.split("–")[0]);
            this.awayScorePen = Integer.parseInt(penScore.split("–")[1]);
        }
    }

    private void sortListGoal() {
        Collections.sort(this.goalList);
    }

    @Override
    public String toString() {
        return ngay;
    }
}
