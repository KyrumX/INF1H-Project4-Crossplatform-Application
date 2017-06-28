package com.paladinzzz.game.player;

//Class voor het generen van players en bijhouden/uploaden van score

import com.paladinzzz.game.database.Database;

public class Player {
    private String playerName;
    private Boolean levelOneDone;
    private Boolean levelTwoDone;
    private Database db;

    public Player(String playerName, Database db) {
        this.playerName = playerName;
        this.db = db;
    }

    private void verifyPlayer() {
        db.verifyPlayer(playerName);
    }

    private String getName() {
        return playerName;
    }

    public static void main (String[] arg) {
        Database database = new Database();
        database.getData(database.connect());
        Player p1 = new Player("ralph", database);
        if(!database.verifyPlayer(p1.getName())) {
            System.out.println("Nieuwe speler aanmaken");
            database.makePlayer(p1.getName());
        }
        else
            System.out.println("Geen nieuwe speler aanmaken");
    }
}
