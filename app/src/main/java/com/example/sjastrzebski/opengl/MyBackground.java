package com.example.sjastrzebski.opengl;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

class MyBackground {

    protected Context theContext;

    protected int thenProgram;
    protected int thenPositionHandle;
    protected int thenTextureHandle;
    protected int thenTextureCoordinatesHandle;
    protected int thenTextureScrollYHandle;

    protected int thenTextureID;

    protected FloatBuffer theVB;

    protected float thefScrollSpeed = 0.6f;
    protected float thefScrollY = 0.0f;

    private final String thestrVertexShaderCode =
            "attribute vec4 vPosition;\n"
                    + "uniform float fTextureScrollY;\n"
                    + "attribute vec2 vTextureCoordinatesIn;\n"
                    + "varying   vec2 vTextureCoordinates;\n"
                    + "void main(){\n"
                    + " vTextureCoordinates=vTextureCoordinatesIn+vec2(0,fTextureScrollY);\n"
                    + " gl_Position = vPosition;\n"
                    + "}\n";

    private final String thestrFragmentShaderCode =
            "precision mediump float;\n"        //highp, lowp
                    + "uniform sampler2D u_Texture;\n"
                    + "varying vec2 vTextureCoordinates;\n"
                    + "void main(){\n"
                    + " gl_FragColor = texture2D(u_Texture, vTextureCoordinates);\n"
                    + "}\n";

    public MyBackground(Context aContext) {
        theContext = aContext;

        thenTextureID = UtilsTexture.loadTexture(theContext, R.drawable.background);
        thenProgram = UtilsShaders.createShadersProgram(thestrVertexShaderCode, thestrFragmentShaderCode);

        thenPositionHandle = GLES20.glGetAttribLocation(thenProgram, "vPosition");
        thenTextureHandle = GLES20.glGetUniformLocation(thenProgram, "u_Texture");
        thenTextureCoordinatesHandle = GLES20.glGetAttribLocation(thenProgram, "vTextureCoordinatesIn");
        thenTextureScrollYHandle = GLES20.glGetUniformLocation(thenProgram, "fTextureScrollY");

        initShapes();
    }

    protected void initShapes() {
        float avfTriangleCoords[] = {
                // X, Y, Z, tU,tV
                -1.0f, -1.0f, 0, 0.0f, 0.0f,
                1.0f, -1.0f, 0, 1.0f, 0.0f,
                -1.0f, 1.0f, 0, 0.0f, 1.0f,
                1.0f, 1.0f, 0, 1.0f, 1.0f
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

        GLES20.glUniform1f(thenTextureScrollYHandle, thefScrollY);

        // Prepare the triangle data
        //int indx, int size, int type, boolean normalized, int stride, Buffer ptr
        //stride=3+2 float * 4 bytes= 20 bytes
        //normalized==GL_TRUE indicates that values stored in an integer format are to be mapped to the range [-1,1] (for signed values) or [0,1] (for unsigned values)
        theVB.position(0); // set the buffer to read the first coordinate
        GLES20.glVertexAttribPointer(thenPositionHandle, 3, GLES20.GL_FLOAT, false, 20, theVB);
        GLES20.glEnableVertexAttribArray(thenPositionHandle);

        theVB.position(3); //offset dla współrzędnych tekstury
        GLES20.glVertexAttribPointer(thenTextureCoordinatesHandle, 2, GLES20.GL_FLOAT, false, 20, theVB);
        GLES20.glEnableVertexAttribArray(thenTextureCoordinatesHandle);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }

    public void update(double adCurrentTime, double adElapsedTime) {
        thefScrollY += adElapsedTime*thefScrollSpeed;
        thefScrollY %= 2.0f;
    }
}
