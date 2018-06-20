package com.example.sjastrzebski.opengl;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {
MediaPlayer Song;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Song = MediaPlayer.create(MainMenu.this, R.raw.sound);

    }

    public void startGame(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        Song.start();
    }

}




