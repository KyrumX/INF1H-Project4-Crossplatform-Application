package com.paladinzzz.game.util;

public final class Constants {
    public static final boolean DEBUGGER_ON = true;
    public static final int V_WIDTH = 480;
    public static final int V_HEIGHT = 270;
    public static final int WIDTH = 720;
    public static final int HEIGHT = 405;
    public static final float PPM = 100; //Pixels per meter, om te scalen

    //Hieronder staan de BITS voor objecten, deze kunnen we gebruiken om objecten met elkaar te laten botsen of juist niet:
    //We gebruiken SHORTS omdat je bitwise operators erop kunt gebruiken.
    public static final short DEFAULT_BIT = 1;
    public static final short MOLE_BIT = 2;
    public static final short GROUND_BIT = 4;
    public static final short POLYGON_BIT = 8;
    public static final short BOUNCY_BIT = 16;
    public static final short FLUID_BIT = 32;
    public static final short ANT_BIT = 64;
    public static final short ANT_STOP_BIT = 128;
    public static final short WURRUMPIE_BIT = 256;
    public static final short FINISH_BIT = 512;
}
