package com.example.sjastrzebski.opengl;


import android.content.Context;

import java.util.LinkedList;
import java.util.List;

class MyObstacleSpawner {

    private Context context;
    private LinkedList<MyObstacle> obstacles;

    public MyObstacleSpawner(Context aContext){
        context = aContext;
        obstacles = new LinkedList();
    }



    public void initialize(){
        addObstacle(new Meteorite(context, R.drawable.obstacle));
    }

    public void update(){
        for (MyObstacle o: obstacles) {
            o.update();
        }
    }


    private void addObstacle(MyObstacle obstacle){
        obstacles.add(obstacle);
    }

    public void drawShapes(){
        for (MyObstacle o: obstacles) {
            o.drawShape();
        }
    }

    public LinkedList<vector3f> getPositions(){
        LinkedList<vector3f> pos = new LinkedList<vector3f>();

        for (MyObstacle o: obstacles) {
            pos.add(new vector3f(o.x, o.y, o.z));
        }

        return pos;
    }


}
