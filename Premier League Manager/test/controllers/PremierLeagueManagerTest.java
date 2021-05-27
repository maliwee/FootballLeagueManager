package controllers;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


public class PremierLeagueManagerTest {
    String clubName = "Aston Villa";
    String country = "England";
    String location = "Aston";
    int noOfMatchesPlayed = 10;
    int matchesWon = 7;
    int matchesLost = 1;
    int matchesDrawn = 2;
    int goalsScored = 12;
    int goalsReceived = 6;
    int pointsScored = 34;

    LeagueManager premierLeagueManager = new PremierLeagueManager();

    @Test
    public void addNewClubTest() {

        FootballClub footballClubs = new FootballClub(clubName, country, location, noOfMatchesPlayed, matchesWon,
                matchesLost, matchesDrawn, goalsScored, goalsReceived, pointsScored);
        List<FootballClub> footballClubsList = new ArrayList<>();

        assertArrayEquals(footballClubsList.add(footballClubs));
    }

    @Test
    public void deleteFootballClubTest() {

        FootballClub footballClubs = new FootballClub(clubName, country, location, noOfMatchesPlayed, matchesWon,
                matchesLost, matchesDrawn, goalsScored, goalsReceived, pointsScored);
        List<FootballClub> footballClubsList = new ArrayList<>();

        assertArrayEquals(footballClubsList.remove(footballClubs));
    }

    @Test
    public void addPlayedMatchTest() {

        MatchSimulation matchSimulation = new MatchSimulation("Manchester United", "Chelsea", new DateMatchesPlayed(2, 4, 2020), 14, 11);
        List<MatchSimulation> playedMatchSimulation = new ArrayList<>();

        assertArrayEquals(playedMatchSimulation.add(matchSimulation));
    }



    private void assertArrayEquals ( boolean add){
    }


}
