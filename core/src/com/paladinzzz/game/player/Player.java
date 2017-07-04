package com.paladinzzz.game.player;

//Class voor het generen van players en bijhouden/uploaden van score

import com.paladinzzz.game.database.JSONfunctions;
import com.paladinzzz.game.util.CurrentLevel;

public class Player {
    private String playerName;
    public Boolean levelOneDone = false;
    public Boolean levelTwoDone = false;
    private int playerScore;
    public CurrentLevel worldAndLevelData;

    public Player(String playerName) {
        this.playerName = playerName;
        this.worldAndLevelData = new CurrentLevel(1);
    }


    public String getName() {
        return playerName;
    }

    public void addPoints() {
        this.playerScore += 10;
    }

    public void reducePoints() {
        if (!(playerScore == 0))
            this.playerScore -= 10;
    }

    public int getScore() {
        return this.playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void addPlayer(){
        JSONfunctions json = new JSONfunctions();
        json.setnewplayer(playerName);
    }

    public void resetScore() {
        this.playerScore = 0;
    }

}


