package com.example.sjastrzebski.opengl;

public class GameCharacter {
    private String playerName;
    private vector3f pos;

    public GameCharacter(vector3f initialPos){
        pos = new vector3f(initialPos);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public vector3f getPos() {
        return pos;
    }

    public void setPos(vector3f pos) {
        this.pos = pos;
    }
}
