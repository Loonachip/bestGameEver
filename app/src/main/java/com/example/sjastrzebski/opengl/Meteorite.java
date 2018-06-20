package com.example.sjastrzebski.opengl;

import android.content.Context;
import android.util.Log;

public class Meteorite extends MyObstacle {

    protected float speed = 0.02f;

    public Meteorite(Context c, int texture) {
        super(c, texture);


    }

    @Override
    public void update() {
        y = y - speed;
    }
}