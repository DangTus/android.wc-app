package com.reddog.worldcup2022.model;

public class Team {
    private String name = null;
    private String logo = null;
    private int position = 0;
    private int played = 0;
    private int won = 0;
    private int drawn = 0;
    private int lost = 0;
    private int goalDiffience = 0;
    private int points = 0;

    public Team() {
    }

    public Team(String name, String logo) {
        this.name = name;
        this.logo = logo;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
