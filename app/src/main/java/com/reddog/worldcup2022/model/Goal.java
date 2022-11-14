package com.reddog.worldcup2022.model;

public class Goal {
    private String player;
    private String time;
    private String type;

    public Goal() {
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
        if(type.equals("pen")){
            this.type="Penalty";
        }else if(type.equals("o.g")){
            this.type="Own goal";
        }else{
            this.type=type;
        }
    }

    public Goal(String player, String time, String type) {
        this.player = player;
        this.time = time;
        this.type = type;
    }
}


