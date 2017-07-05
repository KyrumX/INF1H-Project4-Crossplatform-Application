package com.paladinzzz.game.database;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.paladinzzz.game.screens.LoginScreen.playername;

//This class parses json, so that the retrieved data can be used in our game

public class parseJSON {
    String[] a = new String[255];
    String[] b = new String[255];
    String namelist;
    String x;
    public parseJSON(String x){
        this.x = x;
    }

    public String[] getNames() {
        JSONArray json = new JSONArray(x);
        for(int i=0;i<json.length();i++){
            JSONObject e = json.getJSONObject(i);
            a[i] = e.getString("name");
        }

        return a;

    }

    public String[] getScores() {
        JSONArray json = new JSONArray(x);
        for(int i=0;i<json.length();i++){
            JSONObject e = json.getJSONObject(i);
            a[i] = e.getString("score");
        }

        return a;

    }

    public int getPlayerScore() {
        JSONArray json = new JSONArray(x);
        int c = 0;
        for(int i=0;i<json.length();i++) {
            JSONObject e = json.getJSONObject(i);
            c += Integer.parseInt(e.getString("score"));
        }
        return c;
    }

    public int getHasLevel(String haslevel) {
        JSONArray json = new JSONArray(x);
        int c = 0;
        for(int i=0;i<json.length();i++) {
            JSONObject e = json.getJSONObject(i);
            c += Integer.parseInt(e.getString(haslevel));
        }
        return c;
    }

}


