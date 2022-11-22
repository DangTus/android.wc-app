package com.reddog.worldcup2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.reddog.worldcup2022.model.Goal;
import com.reddog.worldcup2022.model.Match;

import org.w3c.dom.Text;

import java.util.List;

public class MatchDetailActivity extends AppCompatActivity {
    private TextView txtStage, txtDay, txtTime, txtHomeScore, txtHomeName, txtAwayScore, txtAwayName;
    private ImageView imgHome, imgAway;
    private LinearLayout listScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        anhXa();

        Intent intent = getIntent();

        if (intent != null) {
            Match match = (Match) intent.getSerializableExtra("data_match");
            setInfo(match);
            setPlayerScore(match.getGoalList());
        }
    }

    private void setInfo(Match match) {
        txtStage.setText(match.getStage());
        txtDay.setText(match.getDate());
        txtTime.setText(match.getTime());
        txtHomeName.setText(match.getHomeT().getName());
        txtAwayName.setText(match.getAwayT().getName());
        Glide.with(this).load(match.getHomeT().getLogo()).into(imgHome);
        Glide.with(this).load(match.getAwayT().getLogo()).into(imgAway);

        //set score
        if (match.getHomeScore() == -1 && match.getAwayScore() == -1) {
            txtHomeScore.setTextSize(0);
            txtAwayScore.setTextSize(0);
        } else {
            txtHomeScore.setText(String.valueOf(match.getHomeScore()));
            txtAwayScore.setText(String.valueOf(match.getAwayScore()));
        }

        // set color team win
        if (match.getHomeScore() > match.getAwayScore()) {
            txtHomeScore.setTextColor(getResources().getColor(R.color.picton_blue));
            txtHomeName.setTextColor(getResources().getColor(R.color.picton_blue));
        } else if (match.getHomeScore() < match.getAwayScore()) {
            txtAwayScore.setTextColor(getResources().getColor(R.color.picton_blue));
            txtAwayName.setTextColor(getResources().getColor(R.color.picton_blue));
        }
    }

    private void setPlayerScore(List<Goal> goalList) {
        for (Goal goal : goalList) {
            LinearLayout scoreLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.row_goal, listScore, false);

            //anh xa
            TextView txtType = scoreLayout.findViewById(R.id.type_score);
            TextView txtPlayer = scoreLayout.findViewById(R.id.player_score);

            //set data
            String type = goal.getType() + "(" + goal.getTime() + "')";
            txtType.setText(type);

            txtPlayer.setText(goal.getPlayer());

            if(goal.isTeam()) {
                scoreLayout.setGravity(Gravity.LEFT);
            } else {
                scoreLayout.setGravity(Gravity.RIGHT);
            }

            //add to layout
            listScore.addView(scoreLayout);
        }
    }

    private void anhXa() {
        txtStage = findViewById(R.id.stage_textview);
        txtDay = findViewById(R.id.day_textview);
        txtTime = findViewById(R.id.time_textview);
        txtHomeScore = findViewById(R.id.home_socre);
        txtHomeName = findViewById(R.id.home_name);
        txtAwayScore = findViewById(R.id.away_score);
        txtAwayName = findViewById(R.id.away_name);

        imgHome = findViewById(R.id.home_image);
        imgAway = findViewById(R.id.away_image);

        listScore = findViewById(R.id.list_score);
    }
}