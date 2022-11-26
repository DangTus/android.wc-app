package com.reddog.worldcup2022.module;

import android.widget.Toast;

import com.reddog.worldcup2022.model.NewsVideo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsVideoModule {

    public static List<NewsVideo> getListNewsVideo(JSONArray items) {
        List<NewsVideo> newsVideoList = new ArrayList<>();

        int newsVideoLen = items.length();
        for (int i = 0; i < newsVideoLen; i++) {
            try {
                JSONObject newVideoObject = items.getJSONObject(i);

                String videoID = "https://www.youtube.com/watch?v=" + newVideoObject.getJSONObject("id").getString("videoId");
                String title = newVideoObject.getJSONObject("snippet").getString("title");
                String thumbnail = newVideoObject.getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url");

                NewsVideo newsVideo = new NewsVideo(title, thumbnail, videoID);
                newsVideoList.add(newsVideo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return newsVideoList;
    }
}