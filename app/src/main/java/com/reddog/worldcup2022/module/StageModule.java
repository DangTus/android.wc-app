package com.reddog.worldcup2022.module;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StageModule {

    public static List<String> getNameStage(JSONArray response, String stage) {
        List<String> groupNameList = new ArrayList<>();
        try {
            int responseLen = response.length();
            for(int i=0; i<=responseLen; i++) {
                JSONObject object = response.getJSONObject(i);

                if(object.getString("stage").equals(stage)) {
                    JSONArray groupNameListJson = object.getJSONArray("nameStage");

                    int groupNameListLen = groupNameListJson.length();
                    for(int j=0; j<groupNameListLen; j++) {
                        groupNameList.add(groupNameListJson.getString(j));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return groupNameList;
    }
}
