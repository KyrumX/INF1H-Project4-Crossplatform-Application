package com.paladinzzz.game.database;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class JSONfunctions{

    public static final String MY_JSON ="MY_JSON";

    private static final String api_url = "http://www.wemoney.nl/connect.php";


    public String doInBackground() {

        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(api_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String json;
            while((json = bufferedReader.readLine())!= null){
                sb.append(json+"\n");
            }

            return sb.toString().trim();

        }catch(Exception e){
            return null;
        }

    }

    public void doInBackground2(String userurl) {
        try {
            URL yahoo = new URL(userurl);
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch(Exception e){

        }

    }

    public String checkforhighscore(String userurl) {
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(userurl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String json;
            while((json = bufferedReader.readLine())!= null){
                sb.append(json+"\n");
            }
            System.out.println("checkforhighscore loop");
            return sb.toString().trim();

        }catch(Exception e){
            return null;
        }
    }

    public void setnewplayer(String user) {
        try {
            String theurl = "http://www.wemoney.nl/newuser.php?user=" + user;
            URL yahoo = new URL(theurl);
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch(Exception e){

        }

    }

    public int getHasLevel(String haslevel, String user) {
        String theurl = "http://www.wemoney.nl/haslevel.php?user=" + user + "&level=" + haslevel;
        System.out.println(theurl);
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(theurl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String json;
            while((json = bufferedReader.readLine())!= null){
                sb.append(json+"\n");
            }
            System.out.println("checkforhighscore loop");

            parseJSON parser = new parseJSON(sb.toString().trim());
            return parser.getHasLevel(haslevel);



        }catch(Exception e){
            return 500;
        }

    }

    public void sethaslevel(String user, String level, int value) {
        try {
            String theurl = "http://www.wemoney.nl/setlevel.php?user=" + user + "&level=" + level + "&value=" + value;
            URL yahoo = new URL(theurl);
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch(Exception e){

        }

    }

}