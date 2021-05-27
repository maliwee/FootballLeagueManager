package controllers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SchoolFootballClubTest {
    String schoolName="Vidura College";
    int noOfPlayers=15;//number of players in the school football club
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

    FootballClub schoolfootballClub=new SchoolFootballClub(schoolName,noOfPlayers,clubName,country,location,noOfMatchesPlayed,
            matchesWon,matchesLost,matchesDrawn,goalsScored,goalsReceived,pointsScored);


    @Test
    public void SchoolNameTest() {
        assertEquals(schoolName,((SchoolFootballClub) schoolfootballClub).getSchoolName());

    }

    @Test
    public void noOfPlayersTest() {
        assertEquals(noOfPlayers,((SchoolFootballClub) schoolfootballClub).getNoOfPlayers());

    }

    @Test
    public void clubNameTest() {
        assertEquals(clubName,schoolfootballClub.getClubName());
    }

    @Test
    public void countryTest() {
        assertEquals(country,schoolfootballClub.getCountry());
    }

    @Test
    public void locationTest() {
        assertEquals(location,schoolfootballClub.getLocation());
    }

    @Test
    public void noOfMatchesPlayedTest() {
        assertEquals(noOfMatchesPlayed,schoolfootballClub.getNoOfMatchesPlayed());
    }

    @Test
    public void matchesWonTest() {
        assertEquals(matchesWon, schoolfootballClub.getMatchesWon());
    }

    @Test
    public void matchesLostTest() {
        assertEquals(matchesLost,schoolfootballClub.getMatchesLost());
    }

    @Test
    public void matchesDrawnTest() {
        assertEquals(matchesDrawn,schoolfootballClub.getMatchesDrawn());
    }

    @Test
    public void goalsScoredTest() {
        assertEquals(goalsScored,schoolfootballClub.getGoalsScored());
    }

    @Test
    public void goalsReceivedTest() {
        assertEquals(goalsReceived,schoolfootballClub.getGoalsReceived());
    }

    @Test
    public void pointsScoredTest() {
        assertEquals(pointsScored,schoolfootballClub.getPointsScored());
    }

}
