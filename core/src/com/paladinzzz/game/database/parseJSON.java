package com.paladinzzz.game.database;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Selim on 28/6/2017.
 */

public class parseJSON {
    String x;
    public parseJSON(String x){
        this.x = x;
    }

    public void getData() {
        System.out.println(x);
        JSONArray json = new JSONArray(x);
        for(int i=0;i<json.length();i++){
            JSONObject e = json.getJSONObject(i);
            System.out.println(e.getString("name"));
            System.out.println(e.getString("score"));
        }
    }
}
