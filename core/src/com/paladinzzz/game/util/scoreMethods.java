package com.paladinzzz.game.util;

import com.paladinzzz.game.database.JSONfunctions;
import com.paladinzzz.game.database.parseJSON;
import static com.paladinzzz.game.screens.LoginScreen.playername;

//De methodes die we gebruiken voor score

public class scoreMethods {
    public static void grantPoints(){
        //Zet de score van de lokale speler over naar de server
        JSONfunctions json = new JSONfunctions();
        if (playerMemory.isConnected) {
            json.checkforhighscore("http://www.wemoney.nl/getpoints.php?user=" + playername + "&userpoints=" + playerMemory.player.getScore());
            System.out.println("Granted " + playername + " points");
        }
    }

    public static int getUserOnlineScore(){
        JSONfunctions json = new JSONfunctions();
        String userurl = "http://www.wemoney.nl/getuserpoints.php?user=" + playername;
        parseJSON highscoreparser = new parseJSON(json.checkforhighscore(userurl));
        return highscoreparser.getPlayerScore();
    }

    public static void score() {
        if (playerMemory.isConnected) {
            int localplayerscore = playerMemory.player.getScore();
            int onlinescore = getUserOnlineScore();
            if (localplayerscore > onlinescore) {
                grantPoints();
            }
        }
    }
}
