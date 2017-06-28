package com.paladinzzz.game.util;
public class CurrentLevel {
    public int currentWorld;
    public int currentLevel;

    public CurrentLevel(int setWorld) {
        currentWorld = setWorld;
        currentLevel = 1;
    }

    public void setCurrentWorld(int setWorld) {
        this.currentWorld = setWorld;
    }

    public void setCurrentLevel(int setLevel) {
        this.currentLevel = setLevel;
    }
}
