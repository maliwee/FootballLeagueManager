package controllers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UniversityFootballClubTest {

        String universityName="IIT";
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

        FootballClub universityfootballClub=new UniversityFootballClub(universityName,noOfPlayers,clubName,country,location,noOfMatchesPlayed,
                matchesWon,matchesLost,matchesDrawn,goalsScored,goalsReceived,pointsScored);


        @Test
        public void SchoolNameTest() {
            assertEquals(universityName,((UniversityFootballClub) universityfootballClub).getUniversityName());

        }

        @Test
        public void noOfPlayersTest() {
            assertEquals(noOfPlayers,((UniversityFootballClub) universityfootballClub).getNoOfPlayers());

        }

        @Test
        public void clubNameTest() {
            assertEquals(clubName,universityfootballClub.getClubName());
        }

        @Test
        public void countryTest() {
            assertEquals(country,universityfootballClub.getCountry());
        }

        @Test
        public void locationTest() {
            assertEquals(location,universityfootballClub.getLocation());
        }

        @Test
        public void noOfMatchesPlayedTest() {
            assertEquals(noOfMatchesPlayed,universityfootballClub.getNoOfMatchesPlayed());
        }

        @Test
        public void matchesWonTest() {
            assertEquals(matchesWon, universityfootballClub.getMatchesWon());
        }

        @Test
        public void matchesLostTest() {
            assertEquals(matchesLost,universityfootballClub.getMatchesLost());
        }

        @Test
        public void matchesDrawnTest() {
            assertEquals(matchesDrawn,universityfootballClub.getMatchesDrawn());
        }

        @Test
        public void goalsScoredTest() {
            assertEquals(goalsScored,universityfootballClub.getGoalsScored());
        }

        @Test
        public void goalsReceivedTest() {
            assertEquals(goalsReceived,universityfootballClub.getGoalsReceived());
        }

        @Test
        public void pointsScoredTest() {
            assertEquals(pointsScored,universityfootballClub.getPointsScored());
        }

    }

