package com.example.spoilerfreehighlights;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra(MainActivity.VIDEO_URL);


        VideoView video = (VideoView) findViewById(R.id.highlightsVideo);
        MediaController mc = new MediaController(this);
        mc.setAnchorView(video);
        mc.setMediaPlayer(video);
        Uri uri = Uri.parse(videoUrl);
        video.setMediaController(mc);
        video.setVideoURI(uri);
        DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) video.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        video.setLayoutParams(params);
        video.start();
    }
}
