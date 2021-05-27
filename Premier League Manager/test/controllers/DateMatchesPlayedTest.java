package controllers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateMatchesPlayedTest {
    int day=5;
    int month=3;
    int year=2020;

    DateMatchesPlayed dateMatchesPlayed=new DateMatchesPlayed(day,month,year);

    @Test
    public void dayTest() {
        assertEquals(day,dateMatchesPlayed.getDay());
    }

    @Test
    public void monthTest() {
        assertEquals(month,dateMatchesPlayed.getMonth());
    }

    @Test
    public void yearTest() {
        assertEquals(year,dateMatchesPlayed.getYear());

    }
}
