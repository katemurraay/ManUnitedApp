package com.kmm.a117349221_assignment1;

import android.content.Context;

import com.kmm.a117349221_assignment1.model.Player;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLData {

    private Player data [];
    private Context context;

    public XMLData(Context c){
        this.context = c;

        //get data parse from xml
        InputStream stream = context.getResources().openRawResource(R.raw.players);

        DocumentBuilder builder = null;
        Document document = null;

        try{
            //make a builder and parse stream
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(stream);

        }
        catch (Exception e) {

            e.printStackTrace();
        }
        NodeList idList = document.getElementsByTagName("id");
        NodeList ageList = document.getElementsByTagName("age");
        NodeList nameList = document.getElementsByTagName("name");
        NodeList positionList = document.getElementsByTagName("position");
        NodeList imageList = document.getElementsByTagName("image");
        NodeList teamList = document.getElementsByTagName("team");
        data = new Player[nameList.getLength()];

        for (int i = 0; i < data.length; i ++){
            String id = idList.item(i).getFirstChild().getNodeValue();
            String age = ageList.item(i).getFirstChild().getNodeValue();
            String name = nameList.item(i).getFirstChild().getNodeValue();
            String position = positionList.item(i).getFirstChild().getNodeValue();
            String team = teamList.item(i).getFirstChild().getNodeValue();
            String image = imageList.item(i).getFirstChild().getNodeValue();


            data[i] = new Player(id, age, name, position, image,team);

        }
    }

    public Player getPerson (int i){
        return data[i];
    }

    public int getLength(){return data.length;}

    public String[] getNames(){
        String names [] = new String[this.data.length];
        for(int i = 0; i < names.length; i++){
            names[i] = data[i].getName();

        }
        return names;
    }
    public Player[] getData(){
        return data;
    }
}
