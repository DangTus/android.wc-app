package com.reddog.worldcup2022.module;

import com.reddog.worldcup2022.model.Goal;
import com.reddog.worldcup2022.model.Match;
import com.reddog.worldcup2022.model.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MatchModule {

    private static Team setTeam(JSONObject object, String type) {
        Team team = new Team();

        try {
            team.setName(object.getJSONObject(type).getString("name"));
            team.setLogo(object.getJSONObject(type).getString("icon"));
        } catch (JSONException e) {
            team.setName("Sai");
            team.setLogo("Sai2");
            e.printStackTrace();
        }

        return team;
    }

    private static List<Goal> setGoal(JSONObject objectJSON) {
        List<Goal> goalList = new ArrayList<>();

        try {
            //get goal home team
            JSONArray arrayHome = objectJSON.getJSONArray("homeG");

            int arrayHomeLen = arrayHome.length();
            for (int i = 0; i < arrayHomeLen; i++) {
                Goal goal = new Goal();
                JSONObject obj = arrayHome.getJSONObject(i);

                goal.setPlayer(obj.getString("player"));
                goal.setTime(obj.getString("timeG"));
                goal.setType(obj.getString("type"));
                goal.setTeam(true);

                goalList.add(goal);
            }


            //get goal away team
            JSONArray arrayAway = objectJSON.getJSONArray("awayG");

            int arrayAwayLen = arrayAway.length();
            for (int i = 0; i < arrayAwayLen; i++) {
                Goal goal = new Goal();
                JSONObject obj = arrayAway.getJSONObject(i);

                goal.setPlayer(obj.getString("player"));
                goal.setTime(obj.getString("timeG"));
                goal.setType(obj.getString("type"));
                goal.setTeam(false);

                goalList.add(goal);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return goalList;
    }

    public static List<Match> getMatch(JSONArray matchLishJson) {
        List<Match> matchList = new ArrayList<>();

        int matchLen = matchLishJson.length();
        for (int i = 0; i < matchLen; i++) {
            try {
                JSONObject obj = matchLishJson.getJSONObject(i);

                Match match = new Match();

                Team awayT = setTeam(obj, "awayT");
                Team homeT = setTeam(obj, "homeT");

                match.setHomeT(homeT);
                match.setAwayT(awayT);

                match.setStatus(obj.getString("score"));
                match.setScore(obj.getString("score"));
                match.setPenScore(obj.getString("scorePen"));

                match.setGoalList(setGoal(obj));

                match.setDate(obj.getString("date"));
                match.setTime(obj.getString("time"));

                matchList.add(match);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return matchList;
    }
}
