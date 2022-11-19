package com.reddog.worldcup2022.module;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GroupModule {

    public static String idToName(String idGroup) {
        String[] array = idGroup.split("_");
        String nameGroup = "";

        for (String value : array) {
            nameGroup += " " + value;
        }

        return nameGroup.trim();
    }
}
