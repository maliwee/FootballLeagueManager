package controllers;

import java.io.Serializable;

public class FootballClub extends SportsClub implements Comparable<FootballClub>,Serializable {
    //attributes that should contain in a football club
        private int goalsScored;
        private int goalsReceived;
        private int pointsScored;

        //constructor for football club
    public FootballClub(String clubName, String country, String location, int noOfMatchesPlayed,
                          int matchesWon, int matchesLost, int matchesDrawn, int goalsScored,
                         int goalsReceived, int pointsScored) {
        super(clubName, country, location, noOfMatchesPlayed, matchesWon, matchesLost, matchesDrawn);
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
        this.pointsScored = pointsScored;
    }

    //getter for get and display the goals scored by a football club
    public int getGoalsScored() {
        return goalsScored;
    }

    //setter method to set the goals scored by a football club entered by the user
    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    //getter for get and display the goals received for a football club
    public int getGoalsReceived() {
        return goalsReceived;
    }

    //setter method to set the goals revived for a football club entered by the user
    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    //getter for get and display the points scored by a football club
    public int getPointsScored() {
        return pointsScored;
    }

    //setter method to set the points scored by a football club entered by the user
    public void setPointsScored(int pointsScored) {
        this.pointsScored = pointsScored;
    }

    //compare the points scored by a football club
    @Override
    public int compareTo(FootballClub footballClub) {
        if (this.pointsScored==footballClub.getPointsScored()){
            return this.goalsScored-footballClub.getGoalsReceived();
        }
        return this.getPointsScored()-footballClub.getPointsScored();
    }


    @Override
    public String toString() {
        return super.toString()+", goalsScored=" + this.goalsScored +", goalsReceived=" + this.goalsReceived +
                ", pointsScored=" + this.pointsScored ;
    }


}