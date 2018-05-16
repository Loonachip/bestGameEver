package com.example.sjastrzebski.opengl;

import android.util.Log;

public class GameCamera {

    private vector3f cameraPos;
    private float cameraAngle;

    public GameCamera(vector3f initialPos){
        cameraPos = new vector3f(initialPos);
        cameraAngle = 0.0f;
    }

    public void setCameraAngle(float value){
        cameraAngle = value;
        Log.d("camera_angle", String.valueOf(value));
    }

    public vector3f getCameraPos() {
        return cameraPos;
    }

    public float getCameraAngle() {
        return cameraAngle;
    }

    public void setCameraPos(vector3f cameraPos) {
        this.cameraPos = cameraPos;
    }
}
