package com.example.sjastrzebski.opengl;

public class vector4f {
    public float r;
    public float g;
    public float b;
    public float a;

    public vector4f(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public vector4f(vector4f v){
        v.r = r;
        v.g = g;
        v.b = b;
        v.a = a;
    }

    public void add(vector4f v){
        this.r += v.r;
        this.g += v.g;
        this.b += v.b;
        this.a += v.a;
    }

    public void substract(vector4f v){
        this.r -= v.r;
        this.g -= v.g;
        this.b -= v.b;
        this.a -= v.a;
    }

    public void clear(){
        this.r = 0.0f;
        this.g = 0.0f;
        this.b = 0.0f;
        this.a = 0.0f;
    }
}
