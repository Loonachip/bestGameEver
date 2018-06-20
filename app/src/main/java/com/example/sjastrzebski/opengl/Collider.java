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
        double x=0;
        double y=0;

        for (int i=0;i<obstacles.size();i++) {
            x = obstacles.get(i).x;
            y = obstacles.get(i).y;
        }
        double x1=ship.x;
        double y1=ship.y;
        double x2=x1+0.1;
        double y2=y1+0.1;
        double x3=x1-0.1;
        double y3=y1-0.1;
        if(x < x1 && y < y1){
            Intent myIntent = new Intent(context, MainMenu.class);
            myIntent.putExtra("SCORE", score);
            context.startActivity(myIntent);
        }
        if(x < x2 && y < y1){
            Intent myIntent = new Intent(context, MainMenu.class);
            myIntent.putExtra("SCORE", score);
            context.startActivity(myIntent);
        }
        if(x < x3 && y < y1){
            Intent myIntent = new Intent(context, MainMenu.class);
            myIntent.putExtra("SCORE", score);
            context.startActivity(myIntent);
        }
        if(x < x2 && y < y1){
            Intent myIntent = new Intent(context, MainMenu.class);
            myIntent.putExtra("SCORE", score);
            context.startActivity(myIntent);
        }
        if(x < x1 && y < y2){
            Intent myIntent = new Intent(context, MainMenu.class);
            myIntent.putExtra("SCORE", score);
            context.startActivity(myIntent);
        }
        if(x < x2 && y < y2){
            Intent myIntent = new Intent(context, MainMenu.class);
            myIntent.putExtra("SCORE", score);
            context.startActivity(myIntent);
        }
        if(x < x3 && y == y2){
            Intent myIntent = new Intent(context, MainMenu.class);
            myIntent.putExtra("SCORE", score);
            context.startActivity(myIntent);
        }
        if(x < x1 && y == y3){
            Intent myIntent = new Intent(context, MainMenu.class);
            myIntent.putExtra("SCORE", score);
            context.startActivity(myIntent);
        }
        if(x < x2 && y == y3){
            Intent myIntent = new Intent(context, MainMenu.class);
            myIntent.putExtra("SCORE", score);
            context.startActivity(myIntent);
        }
        if(x < x3 && y == y3){
            Intent myIntent = new Intent(context, MainMenu.class);
            myIntent.putExtra("SCORE", score);
            context.startActivity(myIntent);
        }
    }
}