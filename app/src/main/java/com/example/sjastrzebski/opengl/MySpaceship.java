package com.example.sjastrzebski.opengl;


import android.content.Context;
import android.nfc.Tag;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

class MySpaceship {
    public float thefSpeed = 0.1f;
    protected int movementFrameSkip = 0;

    protected Context theContext;

    public vector3f position;
    protected int movementFramesSkipped = 0;

    protected int thenProgram;
    protected int thenPositionHandle;
    protected int thenTextureHandle;
    protected int thenTextureCoordinatesHandle;
    protected int thenMatrixHandle;

    protected int thenTextureID;

    protected FloatBuffer theVB;


    protected float[] themScaleMatrix = new float[16];
    protected float[] themMatrix = new float[16];

    protected boolean thebTouchOn;
    protected float thefPositionX;
    protected float thefTouchX, thefTouchY;

    private final String thestrVertexShaderCode =
            "attribute vec4 vPosition;\n"
                    + "uniform mat4 mMatrix;\n"
                    + "attribute vec2 vTextureCoordinatesIn;\n"
                    + "varying   vec2 vTextureCoordinates;\n"
                    + "void main(){\n"
                    + " vTextureCoordinates=vTextureCoordinatesIn;\n"
                    + " gl_Position = mMatrix*vPosition;\n"
                    + "}\n";

    private final String thestrFragmentShaderCode =
            "precision mediump float;\n"
                    + "uniform sampler2D u_Texture;\n"
                    + "varying   vec2 vTextureCoordinates;\n"
                    + "void main(){\n"
                    + " gl_FragColor = texture2D(u_Texture, vTextureCoordinates);\n"
                    + "}\n";


    public MySpaceship(Context aContext) {
        theContext = aContext;

        thenTextureID = UtilsTexture.loadTexture(theContext, R.drawable.spaceship);
        thenProgram = UtilsShaders.createShadersProgram(thestrVertexShaderCode, thestrFragmentShaderCode);

        thenPositionHandle = GLES20.glGetAttribLocation(thenProgram, "vPosition");
        thenTextureHandle = GLES20.glGetUniformLocation(thenProgram, "u_Texture");
        thenTextureCoordinatesHandle = GLES20.glGetAttribLocation(thenProgram, "vTextureCoordinatesIn");
        thenMatrixHandle = GLES20.glGetUniformLocation(thenProgram, "mMatrix");

        Matrix.setIdentityM(themScaleMatrix, 0);
        Matrix.scaleM(themScaleMatrix, 0, 0.1f, 0.1f, 1.0f);

        position = new vector3f(0.0f, -0.7f, 0.0f);

        initShapes();

        thebTouchOn = false;
    }

    protected void initShapes() {
        float avfTriangleCoords[] = {
                // X, Y, Z, tU,tV
                -1.0f, -1.0f, 0, 0.0f, 1.0f,
                1.0f, -1.0f, 0, 1.0f, 1.0f,
                -1.0f, 1.0f, 0, 0.0f, 0.0f,
                1.0f, 1.0f, 0, 1.0f, 0.0f
        };

        // initialize vertex Buffer for triangles
        // (# of coordinate values * 4 bytes per float)
        ByteBuffer vbb = ByteBuffer.allocateDirect(avfTriangleCoords.length * 4);
        vbb.order(ByteOrder.nativeOrder());// use the device hardware's native byte order
        theVB = vbb.asFloatBuffer(); // create a floating point buffer from the ByteBuffer
        theVB.put(avfTriangleCoords); // add the coordinates to the FloatBuffer
        theVB.position(0);
    }

    public void drawShape() {
        GLES20.glUseProgram(thenProgram);

        // Set the active texture unit to texture unit 0.
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        // Bind the texture to this unit.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, thenTextureID);
        // Tell the texture uniform sampler to use this texture in the shader by telling it to read from texture unit 0.
        GLES20.glUniform1i(thenTextureHandle, 0);

        Matrix.setIdentityM(themMatrix, 0);
        Matrix.translateM(themMatrix, 0, position.x, position.y, position.z);
        Matrix.multiplyMM(themMatrix, 0, themMatrix, 0, themScaleMatrix, 0);    //Skalowanie potem translacja

        //int location, int count, boolean transpose, float[] value, int offset
        GLES20.glUniformMatrix4fv(thenMatrixHandle, 1, false, themMatrix, 0);


        // Prepare the triangle data
        //int indx, int size, int type, boolean normalized, int stride, Buffer ptr
        //stride=3+2 float * 4 bytes= 20 bytes
        //normalized==GL_TRUE indicates that values stored in an integer format are to be mapped to the range [-1,1] (for signed values) or [0,1] (for unsigned values)
        theVB.position(0); // set the buffer to read the first coordinate
        GLES20.glVertexAttribPointer(thenPositionHandle, 3, GLES20.GL_FLOAT, false, 20, theVB);
        GLES20.glEnableVertexAttribArray(thenPositionHandle);

        theVB.position(3); //offset dla wspolrzednych tekstury
        GLES20.glVertexAttribPointer(thenTextureCoordinatesHandle, 2, GLES20.GL_FLOAT, false, 20, theVB);
        GLES20.glEnableVertexAttribArray(thenTextureCoordinatesHandle);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }


    public void update(float sensorValue) {

        if(movementFramesSkipped == movementFrameSkip) {

            Log.d("sensorValue", String.valueOf(sensorValue));
            if (sensorValue < -1) {
                thefPositionX += thefSpeed;
            } else if (sensorValue > 1) {
                thefPositionX -= thefSpeed;
            }
            thefPositionX = Math.min(0.8f, Math.max(-0.8f, thefPositionX));

            position.x = thefPositionX;

            movementFramesSkipped = 0;
        }
        else{
            movementFramesSkipped ++;
        }
    }

    public void update(double adCurrentTime, double adElapsedTime, float sensorValue) {
        /*
        if (thebTouchOn) {
            if (thefPositionX < thefTouchX) {
                thefPositionX += adElapsedTime * thefSpeed;
            } else {
                thefPositionX -= adElapsedTime * thefSpeed;
            }
            thefPositionX = Math.min(0.8f, Math.max(-0.8f, thefPositionX));
        }*/
    }

    public void touchOn(float x, float y) {
        //Log.i(LOG_TAG,"x="+x+" y="+y);
        thefTouchX = x;
        thefTouchY = y;
        if (0.0f > y) {    //only when touched bottom part of screen
            thebTouchOn = true;
        }
    }

    public void touchOff() {
        thebTouchOn = false;
    }

}
