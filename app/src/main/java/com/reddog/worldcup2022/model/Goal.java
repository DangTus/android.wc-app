package com.reddog.worldcup2022.model;

import java.io.Serializable;

public class Goal implements Comparable<Goal>, Serializable {
    private String player;
    private String time;
    private String type;
    //true is home team, false is away team
    private boolean team;

    public Goal() {
    }

    public Goal(String player, String time, String type) {
        this.player = player;
        this.time = time;
        this.type = type;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equals("pen")) {
            this.type = "Penalty";
        } else if (type.equals("o.g")) {
            this.type = "Own goal";
        } else {
            this.type = "Bàn thắng";
        }
    }

    public boolean isTeam() {
        return team;
    }

    public void setTeam(boolean team) {
        this.team = team;
    }

    public int getValueTime() {
        if (this.time.indexOf("+") == -1) {
            return Integer.parseInt(this.time);
        } else {
            int time = Integer.parseInt(this.time.split("\\+")[0]) + Integer.parseInt(this.time.split("\\+")[1]);
            return time;
        }
    }

    @Override
    public int compareTo(Goal goal) {
        if (getValueTime() == goal.getValueTime()) {
            return 0;
        } else if (getValueTime() > goal.getValueTime()) {
            return 1;
        } else {
            return -1;
        }
    }
}


