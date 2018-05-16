package com.example.sjastrzebski.opengl;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class GameController  implements SensorEventListener {
    private static final String TAG = "Sensor";
    private SensorManager sensorManager;
    private SensorManager sensorManager2;
    Sensor accelerometer;
    Sensor accelerometer2;
    Game game;

    protected MySpaceship theMySpaceship = null;

    public vector3f xvalue;
    public vector3f xxvalue;

    public GameController(Context context, MySpaceship ship){
        Log.d(TAG, "OnCreate: Initializing Sensor Services");
        sensorManager=(SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager2=(SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        this.theMySpaceship = ship;

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelerometer2 = sensorManager2.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager2.registerListener(this, accelerometer2, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "OnCreate: Registered accelerometer lisner");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d(TAG,
                "onSensorChanged: x: "
                + sensorEvent.values[0]
                + "Y: " + sensorEvent.values[1]
                + "z: " + sensorEvent.values[2]);

        xvalue = new vector3f(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);

        if(theMySpaceship != null)
            theMySpaceship.update(xvalue.x);

        //game.cam.setCameraAngle(sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
