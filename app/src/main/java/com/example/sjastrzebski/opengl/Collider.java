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
        float x=obstacles.get(x);
        float y=obstacles.get(y);
        float z=obstacles.get(z);
        float x1=ship.(x);
        float y1=ship.(y);

        if(obstacles.equals( x && y == x1 && y1 )){


        }
     /*   bool Aabb::Collides(const Aabb & box) const {
            if ( x && y == x1 && y1) {
                return false;
            }
            return true;
        }
        */
     /*
        bool check_collision( SDL_Rect A, SDL_Rect B )
        {

            int leftA, leftB;
            int rightA, rightB;
            int topA, topB;
            int bottomA, bottomB;


            leftA = A.x;
            rightA = A.x + A.w;
            topA = A.y;
            bottomA = A.y + A.h;


            leftB = B.x;
            rightB = B.x + B.w;
            topB = B.y;
            bottomB = B.y + B.h;

            if( bottomA <= topB )
            {
                return false;
            }

            if( topA >= bottomB )
            {
                return false;
            }

            if( rightA <= leftB )
            {
                return false;
            }

            if( leftA >= rightB )
            {
                return false;
            }

            return true;
        }
        */
    }
}
