package controllers;

import java.io.Serializable;

public class SchoolFootballClub extends FootballClub implements Serializable {
    //attributes that a school football club should contain
    private String schoolName;
    private int noOfPlayers;//number of players in the school football club

    //constructor for school football club
    public SchoolFootballClub( String schoolName, int noOfPlayers,String clubName,
                                String country, String location,int noOfMatchesPlayed,
                                int matchesWon, int matchesLost,int matchesDrawn,
                                int goalsScored, int goalsReceived,
                               int pointsScored) {
        super(clubName, country, location, noOfMatchesPlayed, matchesWon, matchesLost, matchesDrawn, goalsScored, goalsReceived, pointsScored);
        this.schoolName = schoolName;
        this.noOfPlayers = noOfPlayers;
    }

    //getter method to get and display the school name
    public String getSchoolName() {
        return schoolName;
    }

    //setter method to set the school name entered by the user
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    //getter method to get and display the number of player
    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    //setter method to set the number of players entered by the user
    public void setNoOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }

    @Override
    public String toString() {
        return super.toString() +"schoolName=" + this.schoolName +", noOfPlayers=" + this.noOfPlayers +"}";
    }
}
