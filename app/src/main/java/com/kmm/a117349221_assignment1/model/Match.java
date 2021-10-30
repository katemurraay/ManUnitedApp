package com.kmm.a117349221_assignment1.model;

import android.util.Log;

public class Match {
    private String playerId, name, date, passes, noPasses, thirdPasses,thirdNoPasses;



    private String tackles, tacklesWon, duels, duelsWon, interceptions, interceptionsWon;
    private String minutesPlayed, redCards, yellowCards;
//<editor-fold desc= "Getters & Setters" default-state="collapsed">
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getMatchName() {
        return name;
    }

    public void setMatchName(String name) {
        this.name = name;
    }
    public String getTackles() {
        return tackles;
    }

    public void setTackles(String tackles) {
        this.tackles = tackles;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPasses() {
        return passes;
    }

    public void setPasses(String passes) {
        this.passes = passes;
    }

    public String getNoPasses() {
        return noPasses;
    }

    public void setNoPasses(String noPasses) {
        this.noPasses = noPasses;
    }

    public String getThirdPasses() {
        return thirdPasses;
    }

    public void setThirdPasses(String thirdPasses) {
        this.thirdPasses = thirdPasses;
    }

    public String getThirdNoPasses() {
        return thirdNoPasses;
    }

    public void setThirdNoPasses(String thirdNoPasses) {
        this.thirdNoPasses = thirdNoPasses;
    }

    public String getTacklesWon() {
        return tacklesWon;
    }

    public void setTacklesWon(String tacklesWon) {
        this.tacklesWon = tacklesWon;
    }

    public String getDuels() {
        return duels;
    }

    public void setDuels(String duels) {
        this.duels = duels;
    }

    public String getDuelsWon() {
        return duelsWon;
    }

    public void setDuelsWon(String duelsWon) {
        this.duelsWon = duelsWon;
    }


    public String getInterceptions() {
        return interceptions;
    }

    public void setInterceptions(String interceptions) {
        this.interceptions = interceptions;
    }

    public String getInterceptionsWon() {
        return interceptionsWon;
    }

    public void setInterceptionsWon(String interceptionsWon) {
        this.interceptionsWon = interceptionsWon;
    }

    public String getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(String minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public String getRedCards() {
        return redCards;
    }

    public void setRedCards(String redCards) {
        this.redCards = redCards;
    }

    public String getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(String yellowCards) {
        this.yellowCards = yellowCards;
    }
//</editor-fold>
    public Match(String playerId, String name, String date, String passes, String noPasses, String thirdPasses, String thirdNoPasses, String tackles, String tacklesWon, String duels, String duelsWon,  String interceptions, String interceptionsWon, String minutesPlayed, String redCards, String yellowCards) {
        this.playerId = playerId;
        this.name = name;
        this.date = date;
        this.passes = passes;
        this.noPasses = noPasses;
        this.thirdPasses = thirdPasses;
        this.thirdNoPasses = thirdNoPasses;
        this.tackles = tackles;
        this.tacklesWon = tacklesWon;
        this.duels = duels;
        this.duelsWon = duelsWon;
        this.interceptions = interceptions;
        this.interceptionsWon = interceptionsWon;
        this.minutesPlayed = minutesPlayed;
        this.redCards = redCards;
        this.yellowCards = yellowCards;
    }


public double getTotalPasses(){
    Log.d("PAsses" , passes);
    Double p = Double.parseDouble(passes);
   Double noP = Double.parseDouble(noPasses);
    return (noP + p);
}
    public int getPercentageTotalPasses(int total){
        int p = Integer.parseInt(passes);
        return ((p/total) * 100);
}

public int getThirdPercentagePasses(){

        int thirdP = Integer.parseInt(thirdPasses);
        int noThirdP = Integer.parseInt(noPasses);
        int total = thirdP + noThirdP;
        return ((thirdP/total) *100 );
}

}
