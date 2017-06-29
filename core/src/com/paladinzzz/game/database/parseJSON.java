package com.paladinzzz.game.database;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Selim on 28/6/2017.
 */

public class parseJSON {
    String[] a = new String[255];
    String[] b = new String[255];
    String namelist;
    String x;
    public parseJSON(String x){
        this.x = x;
    }

    public String[] getNames() {
        System.out.println(x);
        JSONArray json = new JSONArray(x);
        for(int i=0;i<json.length();i++){
            JSONObject e = json.getJSONObject(i);
//            System.out.println(e.getString("name"));
            a[i] = e.getString("name");
//            System.out.println(e.getString("score"));
        }

        return a;

    }

    public String[] getScores() {
        System.out.println(x);
        JSONArray json = new JSONArray(x);
        for(int i=0;i<json.length();i++){
            JSONObject e = json.getJSONObject(i);
//            System.out.println(e.getString("name"));
            a[i] = e.getString("score");
//            System.out.println(e.getString("score"));
        }

        return a;

    }
}
