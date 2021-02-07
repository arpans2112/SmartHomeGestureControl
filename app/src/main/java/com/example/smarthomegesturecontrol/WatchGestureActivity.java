package com.example.smarthomegesturecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import android.net.Uri;

import java.util.Arrays;

public class WatchGestureActivity extends AppCompatActivity {

    private static String GESTURE_TO_PLAY;
    private VideoView mVideoView;
    private int replay_count = 1;
    private String gestureSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_gesture);

        mVideoView = findViewById(R.id.gestureVideo);

        // get the value from intent object received from mainActivity
        Intent intent = getIntent();
        gestureSelected = intent.getStringExtra("gesture_name");
        GESTURE_TO_PLAY = "h_" + gestureSelected.replaceAll(" ", "_").toLowerCase();

    }


    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
        }
    }

    private void initializePlayer() {
        Uri videoUri = getMedia(GESTURE_TO_PLAY);
        mVideoView.setVideoURI(videoUri);
        mVideoView.start();

    }


    private Uri getMedia(String mediaName) {
        return Uri.parse("android.resource://" + getPackageName() +
                "/raw/" + mediaName);
    }


    private void releasePlayer() {
        mVideoView.stopPlayback();
    }


    public void replayVideo(View view) {
        initializePlayer();
    }

    public void goToPracticeScreen(View view) {
        Intent practiceGestureActivityIntent = new Intent(WatchGestureActivity.this, PracticeGestureActivity.class);
        practiceGestureActivityIntent.putExtra("gesture_name", gestureSelected);
        startActivity(practiceGestureActivityIntent);

    }

}