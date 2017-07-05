package com.paladinzzz.game.util;

import com.paladinzzz.game.player.Player;

//Omdat we de Player als object op meerdere plekken nodig kunnen hebben hebben we er een speciale klas voor gemaakt
//Het is niet de beste oplossing ooit, maar het werkt.

public class playerMemory {
    public static Player player;
    public static boolean isConnected;
}
