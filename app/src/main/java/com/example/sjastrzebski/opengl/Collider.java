package com.example.sjastrzebski.opengl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.LinkedList;

public class Collider {

    protected vector3f playerPos;
    protected LinkedList<vector3f> obstaclesPos;
    protected double score;
    protected Context context;

    public Collider(Context c){
        this.context = c;
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

        double xb=0;
        double yb=0;
        double xa=ship.x;
        double ya=ship.y;


        for (int i=0;i<obstacles.size();i++) {
            xb = obstacles.get(i).x;
            yb = obstacles.get(i).y;


            double dx = xb - xa;
            double dy = yb - ya;

            double dystans = Math.sqrt((dx * dx) + (dy * dy));
            double sumaPromieni = 0.16;

            if (dystans <= sumaPromieni) {
                Intent myIntent = new Intent(context, MainMenu.class);
                myIntent.putExtra("SCORE", score);
                context.startActivity(myIntent);
            }
        }
    }
}
