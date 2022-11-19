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

    private static List<Goal> addListGoal(JSONObject objectJSON, List<Goal> goalList, String team) {
        try {
            //get goal home team
            JSONArray goalListJSON = objectJSON.getJSONArray(team);

            int goalListJSONLen = goalListJSON.length();
            for (int i = 0; i < goalListJSONLen; i++) {
                Goal goal = new Goal();
                JSONObject obj = goalListJSON.getJSONObject(i);

                goal.setPlayer(obj.getString("player"));
                goal.setTime(obj.getString("timeG"));
                goal.setType(obj.getString("type"));
                goal.setTeam(team.equals("homeG") ? true : false);

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

                List<Goal> goalList = new ArrayList<>();
                goalList = addListGoal(obj, goalList, "homeG");
                goalList = addListGoal(obj, goalList, "awayG");

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
