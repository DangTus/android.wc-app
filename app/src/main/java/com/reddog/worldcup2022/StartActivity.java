package com.reddog.worldcup2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {
    private VideoView vidIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        anhXa();

        Uri videoURI = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vid_intro);
        vidIntro.setVideoURI(videoURI);

        //start video intro
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                vidIntro.start();
            }
        };
        t.schedule(task, 500);

        //bat su kien khi chay het video
        vidIntro.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        vidIntro = findViewById(R.id.video_intro);
        vidIntro.setZOrderOnTop(true);
    }
}