package com.reddog.worldcup2022.model;

public class Team {
    private String name=null;
    private String logo=null;
    private int possitison=0;
    private int played=0;
    private int won=0;
    private int drawn=0;
    private int lost=0;
    private int goalDiffience=0;
    private int points=0;

    public Team() {
    }

    public Team(String name, String logo, int possitison, int played, int won, int drawn, int lost, int goalDiffience, int points) {
        this.name = name;
        this.logo = logo;
        this.possitison = possitison;
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.goalDiffience = goalDiffience;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getPossitison() {
        return possitison;
    }

    public void setPossitison(int possitison) {
        this.possitison = possitison;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getGoalDiffience() {
        return goalDiffience;
    }

    public void setGoalDiffience(int goalDiffience) {
        this.goalDiffience = goalDiffience;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
