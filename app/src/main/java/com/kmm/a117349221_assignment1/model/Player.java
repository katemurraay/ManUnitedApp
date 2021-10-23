package com.kmm.a117349221_assignment1.model;

import java.io.Serializable;

public class Player implements Serializable {
    private String id;


    private String age;
    private String position;
    private String name;



    private String image;
    private String team;


    private String url;

    public Player(String id, String age, String name, String position, String image, String team, String url ) {
        this.id = id;
        this.age = age;
        this.position = position;
        this.name = name;
        this.image = image;
        this.team =team;
        this.url = url;
    }
    public Player() {
        id = "";
        position ="";
        name ="";
        age = "";
        image ="";
        team ="";
        url = "";

    }

    //<editor-fold default-state="collapsed" desc ="Getters & Setters">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;

    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

//</editor-fold>


}
