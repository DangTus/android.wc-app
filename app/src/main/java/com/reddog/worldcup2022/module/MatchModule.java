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

    public MatchModule() {
    }

    public List<Match> getMatch(JSONArray jsonArray){
        List<Match> list= new ArrayList<>();

        int len = jsonArray.length();
        for(int i=0; i<len; i++){
            try {
                JSONObject obj = jsonArray.getJSONObject(i);

                Match m = new Match();
                Team awayT = new Team();
                Team homeT = new Team();
                List<Goal> awayG = new ArrayList<>();
                List<Goal> homeG = new ArrayList<>();

                awayT.setName(obj.getJSONObject("awayT").getString("name"));
                awayT.setLogo(obj.getJSONObject("awayT").getString("icon"));

                homeT.setName(obj.getJSONObject("homeT").getString("name"));
                homeT.setLogo(obj.getJSONObject("homeT").getString("icon"));

                obj.getJSONArray("awayG");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
