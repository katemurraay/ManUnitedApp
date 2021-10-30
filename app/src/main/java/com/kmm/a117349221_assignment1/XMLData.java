package com.kmm.a117349221_assignment1;

import android.content.Context;
import android.util.Log;

import com.kmm.a117349221_assignment1.model.Match;
import com.kmm.a117349221_assignment1.model.Player;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLData {

    private Player data [];
    private Context context;
    private Match[] matches;

    public XMLData(Context c){
        this.context = c;
    }

    public Player getPerson (int i){
        getData();
        return data[i];
    }

    public int getLength(){return data.length;}

    public String[] getNames(){
        getData();
        String names [] = new String[this.data.length];
        for(int i = 0; i < names.length; i++){
            names[i] = data[i].getName();

        }
        return names;
    }
    public Player[] getData(){
        //get data parse from xml
        InputStream stream = context.getResources().openRawResource(R.raw.players);

        DocumentBuilder builder = null;
        Document document = null;

        try{
            //make a builder and parse stream
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(stream);
            NodeList idList = document.getElementsByTagName("id");
            NodeList ageList = document.getElementsByTagName("age");
            NodeList nameList = document.getElementsByTagName("name");
            NodeList positionList = document.getElementsByTagName("position");
            NodeList appearanceList = document.getElementsByTagName("appearances");
            NodeList dobList = document.getElementsByTagName("dob");
            NodeList joinedList = document.getElementsByTagName("joinedDate");
            NodeList countryList = document.getElementsByTagName("country");
            NodeList imageList = document.getElementsByTagName("image");
            NodeList teamList = document.getElementsByTagName("team");
            NodeList urlList = document.getElementsByTagName("url");
            data = new Player[nameList.getLength()];

            for (int i = 0; i < data.length; i ++){
                String id = idList.item(i).getFirstChild().getNodeValue();
                String age = ageList.item(i).getFirstChild().getNodeValue();
                String name = nameList.item(i).getFirstChild().getNodeValue();
                String position = positionList.item(i).getFirstChild().getNodeValue();
                String appearance = appearanceList.item(i).getFirstChild().getNodeValue();
                String dob = dobList.item(i).getFirstChild().getNodeValue();
                String joinedDate = joinedList.item(i).getFirstChild().getNodeValue();
                String country = countryList.item(i).getFirstChild().getNodeValue();
                String team = teamList.item(i).getFirstChild().getNodeValue();
                String image = imageList.item(i).getFirstChild().getNodeValue();
                String url = urlList.item(i).getFirstChild().getNodeValue();

                data[i] = new Player(id, age, name, position, joinedDate, dob, country, appearance, image,team, url);



            }

        }
        catch (Exception e) {

            e.printStackTrace();
            data = null;
        }

        return data;
    }

    public Match getLastMatch(int id){
       Match tempMatch = null;
       matches =  getMatchData();
        if(matches.length > 0){
            for(Match m : matches){
                int playerId = Integer.parseInt(m.getPlayerId());
                if(playerId == id){
                    tempMatch = m;

                }
            }
        }
        return tempMatch;

    }

    public Match[]  getMatchData(){
        Match[] m ;
        InputStream stream = context.getResources().openRawResource(R.raw.matches);

        DocumentBuilder builder = null;
        Document document = null;

        try{

            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(stream);
            NodeList idList = document.getElementsByTagName("playerId");
            NodeList nameList = document.getElementsByTagName("name");
            NodeList dateList = document.getElementsByTagName("date");
            NodeList passesList = document.getElementsByTagName("passes");
            NodeList noPassesList = document.getElementsByTagName("nopasses");
            NodeList thirdPassList = document.getElementsByTagName("thirdPasses");
            NodeList thirdNoPassesList = document.getElementsByTagName("thirdNoPasses");
            NodeList tacklesList = document.getElementsByTagName("tackles");
            NodeList tacklesWonList = document.getElementsByTagName("tacklesWon");
            NodeList duelsList = document.getElementsByTagName("duels");
            NodeList duelsWonList = document.getElementsByTagName("duelsWon");
            NodeList interceptionsList = document.getElementsByTagName("interceptions");
            NodeList interceptionsWonList = document.getElementsByTagName("interceptionsWon");
            NodeList minutesList = document.getElementsByTagName("minutesPlayed");
            NodeList redCardList = document.getElementsByTagName("redCard");
            NodeList yellowCardList = document.getElementsByTagName("yellowCard");
             m= new Match[nameList.getLength()];

            for (int i = 0; i < m.length; i ++){
                String id = idList.item(i).getFirstChild().getNodeValue();
                String date = dateList.item(i).getFirstChild().getNodeValue();
                String name = nameList.item(i).getFirstChild().getNodeValue();
                String passes =passesList.item(i).getFirstChild().getNodeValue();
                String noPasses = noPassesList.item(i).getFirstChild().getNodeValue();
                String thirdPasses = thirdPassList.item(i).getFirstChild().getNodeValue();
                String thirdNoPasses = thirdNoPassesList.item(i).getFirstChild().getNodeValue();
                String tackles = tacklesList.item(i).getFirstChild().getNodeValue();
                String tacklesWon = tacklesWonList.item(i).getFirstChild().getNodeValue();
                String duels = duelsList.item(i).getFirstChild().getNodeValue();
                String duelsWon = duelsWonList.item(i).getFirstChild().getNodeValue();
                String interceptions = interceptionsList.item(i).getFirstChild().getNodeValue();
                String interceptionsWon = interceptionsWonList.item(i).getFirstChild().getNodeValue();
                String minutes = minutesList.item(i).getFirstChild().getNodeValue();
                String redCard= redCardList.item(i).getFirstChild().getNodeValue();
                String yellowCard= yellowCardList.item(i).getFirstChild().getNodeValue();


               m[i] = new Match(id,name,date,passes, noPasses, thirdPasses, thirdNoPasses,tackles, tacklesWon, duels, duelsWon, interceptions, interceptionsWon,minutes, redCard, yellowCard);



            }

        }
        catch (Exception e) {
            e.printStackTrace();
             m = null;
        }
        return  m;
    }
}
