package controllers;

import java.io.Serializable;

public class MatchSimulation implements Comparable<MatchSimulation>,Serializable {
    private String homeTeam;
    private String opponentTeam;
    private DateMatchesPlayed dateOfMatchPlayed;
    private int goalsScoredHomeTeam;
    private int goalsScoredOpponentTeam;


    public MatchSimulation(String homeTeam, String opponentTeam, DateMatchesPlayed dateOfMatchPlayed,
                           int goalsScoredHomeTeam, int goalsScoredOpponentTeam) {
        this.homeTeam = homeTeam;
        this.opponentTeam = opponentTeam;
        this.dateOfMatchPlayed = dateOfMatchPlayed;
        this.goalsScoredHomeTeam = goalsScoredHomeTeam;
        this.goalsScoredOpponentTeam = goalsScoredOpponentTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getOpponentTeam() {
        return opponentTeam;
    }

    public void setOpponentTeam(String opponentTeam) {
        this.opponentTeam = opponentTeam;
    }

    public DateMatchesPlayed getDateOfMatchPlayed() {
        return dateOfMatchPlayed;
    }

    public void setDateOfMatchPlayed(DateMatchesPlayed dateOfMatchPlayed) {
        this.dateOfMatchPlayed = dateOfMatchPlayed;
    }

    public int getGoalsScoredHomeTeam() {
        return goalsScoredHomeTeam;
    }

    public void setGoalsScoredHomeTeam(int goalsScoredHomeTeam) {
        this.goalsScoredHomeTeam = goalsScoredHomeTeam;
    }

    public int getGoalsScoredOpponentTeam() {
        return goalsScoredOpponentTeam;
    }

    public void setGoalsScoredOpponentTeam(int goalsScoredOpponentTeam) {
        this.goalsScoredOpponentTeam = goalsScoredOpponentTeam;
    }


    @Override
    public String toString() {
        return "MatchSimulation{" +"homeTeam=" + this.homeTeam +", opponentTeam=" + this.opponentTeam  +
                ", dateOfMatchPlaying=" + this.dateOfMatchPlayed +", goalsScoredHomeTeam=" + this.goalsScoredHomeTeam +
                ", goalsScoredOpponentTeam=" + this.goalsScoredOpponentTeam +'}';
    }

    @Override
    public int compareTo(MatchSimulation matchSimulation) {
        if (this.dateOfMatchPlayed.getMonth()==matchSimulation.getDateOfMatchPlayed().getMonth()){
            return this.dateOfMatchPlayed.getDay()-matchSimulation.getDateOfMatchPlayed().getDay();
        }
        return this.dateOfMatchPlayed.getMonth()-matchSimulation.getDateOfMatchPlayed().getMonth();
    }
}
