package controllers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatchSimulationTest {
    String homeTeam="Chelsea";
    String opponentTeam="Liverpool";
    DateMatchesPlayed dateOfMatchPlayed=new DateMatchesPlayed(4,9,2020);
    int goalsScoredHomeTeam=7;
    int goalsScoredOpponentTeam=2;

    MatchSimulation matchSimulation=new MatchSimulation(homeTeam,opponentTeam,dateOfMatchPlayed,goalsScoredHomeTeam,goalsScoredOpponentTeam);

    @Test
    public void homeTeamTest() {
        assertEquals(homeTeam,matchSimulation.getHomeTeam());
    }

    @Test
    public void opponentTeamTest() {
        assertEquals(opponentTeam,matchSimulation.getOpponentTeam());

    }

    @Test
    public void dateOfMatchPlayedTest() {
        assertEquals(dateOfMatchPlayed,matchSimulation.getDateOfMatchPlayed());

    }

    @Test
    public void goalsScoredHomeTeamTest() {
        assertEquals(goalsScoredHomeTeam,matchSimulation.getGoalsScoredHomeTeam());

    }

    @Test
    public void goalsScoredOpponentTeamTest() {
        assertEquals(goalsScoredOpponentTeam,matchSimulation.getGoalsScoredOpponentTeam());

    }
}
