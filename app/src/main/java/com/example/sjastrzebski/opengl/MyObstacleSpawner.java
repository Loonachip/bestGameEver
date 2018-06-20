package com.example.sjastrzebski.opengl;


import android.content.Context;

import java.util.LinkedList;
import java.util.List;

class MyObstacleSpawner {

    private Context context;
    private LinkedList<MyObstacle> obstacles;
    private double lastTime=0;
    public MyObstacleSpawner(Context aContext){
        context = aContext;
        obstacles = new LinkedList();
    }



    public void initialize(){
        addObstacle(new Meteorite(context, R.drawable.obstacle));
    }

    public void update(double timeElapsed){
        for (int i=0;i<obstacles.size();i++) {
            obstacles.get(i).update();
            if(obstacles.get(i).y<-1.2f){
                obstacles.remove(i);
            }
        }

        if(timeElapsed-lastTime>0.5)
        {
            lastTime=timeElapsed;
            addObstacle(new Meteorite(context, R.drawable.obstacle));
            obstacles.getLast().setPosition(new vector3f((float) Math.random()*2-1,1.0f,0.0f));
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
