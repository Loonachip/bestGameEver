package com.example.sjastrzebski.opengl;

public class vector3f {
    public float x;
    public float y;
    public float z;

    public vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public vector3f(vector3f v){
        v.x = x;
        v.y = y;
        v.z = z;
    }

    public void add(vector3f v){
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
    }

    public void substract(vector3f v){
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
    }

    public void clear(){
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
