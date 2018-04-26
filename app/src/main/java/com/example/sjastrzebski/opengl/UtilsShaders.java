package com.example.sjastrzebski.opengl;

import android.opengl.GLES20;
import android.util.Log;

public class UtilsShaders {

    public static int loadShader(int anType, String astrShaderCode) {
        int anShader = GLES20.glCreateShader(anType);
        if (0 == anShader) {
            Log.e(CommonConstants.LOG_TAG, "loadShader failed:" + astrShaderCode);
            return 0;
        }
        GLES20.glShaderSource(anShader, astrShaderCode);
        GLES20.glCompileShader(anShader);
        int[] avnCompiled = new int[1];
        GLES20.glGetShaderiv(anShader, GLES20.GL_COMPILE_STATUS, avnCompiled, 0);
        if (0 == avnCompiled[0]) {
            GLES20.glDeleteShader(anShader);
            Log.e(CommonConstants.LOG_TAG, "glCompileShader failed:" + GLES20.glGetShaderInfoLog(anShader) + "|" + astrShaderCode);
            return 0;
        }
        return anShader;
    }

    public static int createShadersProgram(String astrVertexShaderCode, String astrFragmentShaderCode) {
        int anVertexShader = loadShader(GLES20.GL_VERTEX_SHADER, astrVertexShaderCode);
        if (0 == anVertexShader) {
            return 0;
        }
        int anFragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, astrFragmentShaderCode);
        if (0 == anFragmentShader) {
            GLES20.glDeleteShader(anVertexShader);
            return 0;
        }
        int anProgram = GLES20.glCreateProgram(); // create empty OpenGL Program
        if (0 == anProgram) {
            GLES20.glDeleteShader(anVertexShader);
            GLES20.glDeleteShader(anFragmentShader);
            Log.e(CommonConstants.LOG_TAG, "glCreateProgram failed");
            return 0;
        }
        GLES20.glAttachShader(anProgram, anVertexShader); // add the vertex shader to program
        GLES20.glAttachShader(anProgram, anFragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram(anProgram); // creates OpenGL program executables
        int[] avnLinkStatus = new int[1];
        GLES20.glGetProgramiv(anProgram, GLES20.GL_LINK_STATUS, avnLinkStatus, 0);
        if (GLES20.GL_TRUE != avnLinkStatus[0]) {
            GLES20.glDeleteProgram(anProgram);
            GLES20.glDeleteShader(anVertexShader);
            GLES20.glDeleteShader(anFragmentShader);
            Log.e(CommonConstants.LOG_TAG, "glLinkProgram failed:" + GLES20.glGetProgramInfoLog(anProgram));
            return 0;
        }

        return anProgram;
    }

}
