package com.example.sjastrzebski.opengl;

public class Game {
    private GameCharacter player;
    private GameLevel level;
    private float score;
    public GameCamera cam;

    public Game(){
        player = new GameCharacter(new vector3f(0.0f, 0.0f, 0.0f));
        level = new GameLevel();
        cam = new GameCamera(new vector3f(0.0f, 0.0f, 2.0f));
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getPlayerName(){
        return  player.getPlayerName();
    }

    public void setPlayerName(String name){
         player.setPlayerName(name);
    }

}
