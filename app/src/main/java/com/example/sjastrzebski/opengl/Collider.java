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
        float x=0;
        float y=0;

        for (int i=0;i<obstacles.size();i++) {
            x = obstacles.get(i).x;
            y = obstacles.get(i).y;
        }
        float x1=ship.x;
        float y1=ship.y;

        if( x == x1 && y == y1){
                Intent myIntent = new Intent(context, MainActivity.class);
                context.startActivity(myIntent);
                myIntent.putExtra("SCORE", tutaj jebnij score );
                return ;
            }
            return ;
    }
}
