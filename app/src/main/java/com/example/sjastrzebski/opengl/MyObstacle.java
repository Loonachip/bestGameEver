package com.example.sjastrzebski.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class MyObstacle {
    protected float x;
    protected float y;
    protected float z;

    private Context context;

    private int glesProgram;
    private int glesPositionHandle;
    private int glesTextureHandle;
    private int glesTextureCoordinatesHandle;
    private int glesMatrixHandle;
    private int textureID;

    private FloatBuffer theVB;

    private float[] glesScaleMatrix = new float[16];
    private float[] glesMatrix = new float[16];


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

    public MyObstacle(Context c, int texture){
        this.context = c;
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;

        context = c;


        textureID = UtilsTexture.loadTexture(context, texture);
        glesProgram = UtilsShaders.createShadersProgram(thestrVertexShaderCode, thestrFragmentShaderCode);

        glesPositionHandle = GLES20.glGetAttribLocation(glesProgram, "vPosition");
        glesTextureHandle = GLES20.glGetUniformLocation(glesProgram, "u_Texture");
        glesTextureCoordinatesHandle = GLES20.glGetAttribLocation(glesProgram, "vTextureCoordinatesIn");
        glesMatrixHandle = GLES20.glGetUniformLocation(glesProgram, "mMatrix");

        Matrix.setIdentityM(glesScaleMatrix, 0);
        Matrix.scaleM(glesScaleMatrix, 0, 0.1f, 0.1f, 1.0f);

        initShapes();
    }

    public abstract void update();

    public void setPosition(vector3f newPosition){
        x = newPosition.x;
        y = newPosition.y;
        z = newPosition.z;
    }

    private void initShapes() {
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
        GLES20.glUseProgram(glesProgram);

        // Set the active texture unit to texture unit 0.
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        // Bind the texture to this unit.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureID);
        // Tell the texture uniform sampler to use this texture in the shader by telling it to read from texture unit 0.
        GLES20.glUniform1i(glesTextureHandle, 0);

        Matrix.setIdentityM(glesMatrix, 0);
        Matrix.translateM(glesMatrix, 0, x, y, z);
        Matrix.multiplyMM(glesMatrix, 0, glesMatrix, 0, glesScaleMatrix, 0);    //Skalowanie potem translacja

        //int location, int count, boolean transpose, float[] value, int offset
        GLES20.glUniformMatrix4fv(glesMatrixHandle, 1, false, glesMatrix, 0);


        // Prepare the triangle data
        //int indx, int size, int type, boolean normalized, int stride, Buffer ptr
        //stride=3+2 float * 4 bytes= 20 bytes
        //normalized==GL_TRUE indicates that values stored in an integer format are to be mapped to the range [-1,1] (for signed values) or [0,1] (for unsigned values)
        theVB.position(0); // set the buffer to read the first coordinate
        GLES20.glVertexAttribPointer(glesPositionHandle, 3, GLES20.GL_FLOAT, false, 20, theVB);
        GLES20.glEnableVertexAttribArray(glesPositionHandle);

        theVB.position(3); //offset dla wspolrzednych tekstury
        GLES20.glVertexAttribPointer(glesTextureCoordinatesHandle, 2, GLES20.GL_FLOAT, false, 20, theVB);
        GLES20.glEnableVertexAttribArray(glesTextureCoordinatesHandle);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }
}
