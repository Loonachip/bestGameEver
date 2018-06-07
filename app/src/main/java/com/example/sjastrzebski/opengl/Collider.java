package com.example.sjastrzebski.opengl;

import android.util.Log;

import java.util.LinkedList;

public class Collider {

    protected vector3f playerPos;
    protected LinkedList<vector3f> obstaclesPos;
    protected double score;

    public Collider(){
    }

    private void onUpdate(){
        //TODO: zarządzanie kolizjami

        //Log.d("player", "\nx: " +playerPos.x+ "\ny:" +playerPos.y+ "\nz:" +playerPos.z);
    }

    public void update(vector3f ship, LinkedList<vector3f> obstacles, double time) {
        playerPos = ship;
        obstaclesPos = obstacles;
        score = time;
        onUpdate();

      //  if(obstacles.equals(obstacles.get(y)))
    }
}
