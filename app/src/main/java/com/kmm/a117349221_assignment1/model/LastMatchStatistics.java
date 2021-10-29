package com.kmm.a117349221_assignment1.model;

public class LastMatchStatistics {
    private String playerId, name, date, passes, noPasses, thirdPasses,thirdNoPasses;
    private String tacklesWon, duels, duelsWon, aerialDuels, aerialDuelsWon, interceptions, interceptionsWon;
    private String minutesPlayer, redCards, yellowCards;
//<editor-fold desc= "Getters & Setters" default-state="collapsed">
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAerialDuels() {
        return aerialDuels;
    }

    public void setAerialDuels(String aerialDuels) {
        this.aerialDuels = aerialDuels;
    }

    public String getAerialDuelsWon() {
        return aerialDuelsWon;
    }

    public void setAerialDuelsWon(String aerialDuelsWon) {
        this.aerialDuelsWon = aerialDuelsWon;
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

    public String getMinutesPlayer() {
        return minutesPlayer;
    }

    public void setMinutesPlayer(String minutesPlayer) {
        this.minutesPlayer = minutesPlayer;
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
    public LastMatchStatistics(String playerId, String name, String date, String passes, String noPasses, String thirdPasses, String thirdNoPasses, String tacklesWon, String duels, String duelsWon, String aerialDuels, String aerialDuelsWon, String interceptions, String interceptionsWon, String minutesPlayer, String redCards, String yellowCards) {
        this.playerId = playerId;
        this.name = name;
        this.date = date;
        this.passes = passes;
        this.noPasses = noPasses;
        this.thirdPasses = thirdPasses;
        this.thirdNoPasses = thirdNoPasses;
        this.tacklesWon = tacklesWon;
        this.duels = duels;
        this.duelsWon = duelsWon;
        this.aerialDuels = aerialDuels;
        this.aerialDuelsWon = aerialDuelsWon;
        this.interceptions = interceptions;
        this.interceptionsWon = interceptionsWon;
        this.minutesPlayer = minutesPlayer;
        this.redCards = redCards;
        this.yellowCards = yellowCards;
    }
}
