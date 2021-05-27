package controllers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FootballClubTest {
    String clubName="Manchester United";
    String country="England";
    String location="Manchester";
    int noOfMatchesPlayed=8;
    int matchesWon=5;
    int matchesLost=2;
    int matchesDrawn=1;
    int goalsScored=21;
    int goalsReceived=13;
    int pointsScored=45;

    SportsClub footballClubs=new FootballClub(clubName,country,location,noOfMatchesPlayed,matchesWon,
            matchesLost,matchesDrawn,goalsScored,goalsReceived,pointsScored);
    @Test
    public void clubNameTest() {
        assertEquals(clubName,footballClubs.getClubName());
    }

    @Test
    public void countryTest() {
        assertEquals(country,footballClubs.getCountry());
    }

    @Test
    public void locationTest() {
        assertEquals(location,footballClubs.getLocation());
    }

    @Test
    public void noOfMatchesPlayedTest() {
        assertEquals(noOfMatchesPlayed,footballClubs.getNoOfMatchesPlayed());
    }

    @Test
    public void matchesWonTest() {
        assertEquals(matchesWon, footballClubs.getMatchesWon());
    }

    @Test
    public void matchesLostTest() {
        assertEquals(matchesLost,footballClubs.getMatchesLost());
    }

    @Test
    public void matchesDrawnTest() {
        assertEquals(matchesDrawn,footballClubs.getMatchesDrawn());
    }

    @Test
    public void goalsScoredTest() {
        assertEquals(goalsScored,((FootballClub) footballClubs).getGoalsScored());
    }

    @Test
    public void goalsReceivedTest() {
        assertEquals(goalsReceived,((FootballClub) footballClubs).getGoalsReceived());
    }

    @Test
    public void pointsScoredTest() {
        assertEquals(pointsScored,((FootballClub) footballClubs).getPointsScored());
    }

}
