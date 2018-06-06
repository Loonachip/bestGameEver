package com.example.sjastrzebski.opengl;

import android.content.Context;
import android.util.Log;

public class Meteorite extends MyObstacle {

    protected float speed = 0.08f;

    public Meteorite(Context c, int texture) {
        super(c, texture);
        x = 0.0f;
        y = -0.6f;
    }

    @Override
    public void update() {
        //Log.d("obstacle", "\nx: " +x+ "\ny:" +y+ "\nz:" +z);
        //y -= speed;
    }
}
