package com.paladinzzz.game.player;

//Class voor het generen van players en bijhouden/uploaden van score

import com.paladinzzz.game.database.Database;
import com.paladinzzz.game.database.JSONfunctions;
import com.paladinzzz.game.util.CurrentLevel;

public class Player {
    private String playerName;
    public Boolean levelOneDone = false;
    public Boolean levelTwoDone = false;
    private int playerScore;
    private Database db;
    public CurrentLevel worldAndLevelData;

    public Player(String playerName, Database db) {
        this.playerName = playerName;
        this.db = db;
        this.definePlayer();
        this.worldAndLevelData = new CurrentLevel(1);
    }

    private void verifyPlayer() {
        db.verifyPlayer(playerName, db.connect());
    }

    public String getName() {
        return playerName;
    }

    private void definePlayer() {
        if (!db.verifyPlayer(this.getName(), db.connect())) {
            JSONfunctions makeplayerjson = new JSONfunctions();
            makeplayerjson.setnewplayer(this.getName());
            System.out.println("Nieuwe speler aanmaken");
            db.makePlayer(this.getName(), db.connect());
        } else
            System.out.println("Geen nieuwe speler aanmaken");
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

}


