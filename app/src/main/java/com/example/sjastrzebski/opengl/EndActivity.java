package com.example.sjastrzebski.opengl;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);


        TextView scoreLabel = (TextView) findViewById(R.id.score);
        TextView highscoreLabel = (TextView) findViewById(R.id.highscoreLabel);

        int score = getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore =settings.getInt("HIGH_SCORE", 0);

        if (score > highScore)
        {
            highscoreLabel.setText("High Score : " + score);

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();

        } else {
            highscoreLabel.setText("High Score : " + highScore);
        }
    }
}
