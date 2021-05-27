package controllers;

import java.io.*;
import java.util.*;

//PremierLeagueManager class which is going to implement the classes which are in the interface LeagueManager
public class PremierLeagueManager implements LeagueManager, Serializable {
    public static final int maxNoClubs = 20;//variable showing maximum number of clubs that can play in the premier league
    private List<FootballClub> list_of_footballClubs = new ArrayList<>();//arraylist which contain all the objects in sports club including football clubs
    private List<MatchSimulation> playedMatchesSimulation = new ArrayList<>();//arraylist which contain all the objects in match simulation class

    //Method that is used to create a new football club and add to the premier league manager
    @Override
    public void addToPremierLeague(FootballClub footballClub) {

        for (FootballClub footballClubNew : list_of_footballClubs) {//looping inside the list of football clubs
            if ((footballClub.getClubName().equals(footballClubNew.getClubName()))) {//if the user enters an already entered club, printing an error
                System.out.println("ERROR ! This Football club is already registered");
                System.out.println("\n");
                return;//printing the error message and return to the main menu
            }
        }

        //finding that the university entered by the user is already registered
        if (footballClub instanceof UniversityFootballClub) {
            for (FootballClub footballClubNew : list_of_footballClubs) {
                if (footballClubNew instanceof UniversityFootballClub) {
                    if ((((UniversityFootballClub) footballClubNew).getUniversityName()).equals((((UniversityFootballClub) footballClub).getUniversityName()))) {
                        System.out.println("ERROR ! This UNIVERSITY IS ALREADY REGISTERED");
                        System.out.println("\n");
                        return;
                    }
                }
            }
        }


        //finding that the school entered by the user is already registered
        if (footballClub instanceof SchoolFootballClub) {
            for (FootballClub footballClubNew : list_of_footballClubs) {
                if (footballClubNew instanceof SchoolFootballClub) {
                    if ((((SchoolFootballClub) footballClubNew).getSchoolName()).equals((((SchoolFootballClub) footballClub).getSchoolName()))) {
                        System.out.println("ERROR ! This SCHOOL IS ALREADY REGISTERED");
                        System.out.println("\n");
                        return;
                    }
                }
            }
        }

         if (list_of_footballClubs.size()<maxNoClubs) {//if football club list size is lesser than 20 add football clubs
            list_of_footballClubs.add(footballClub);//if there are no error adding football clubs to the arraylist
            System.out.println("YOU HAVE SUCCESSFULLY ADDED A FOOTBALL CLUB...CHEERS !");
            //printing the number of free slots remaining
             System.out.println("Free Slots remaining : "+(maxNoClubs-list_of_footballClubs.size()));
             System.out.println("\n");
            System.out.println(list_of_footballClubs);
        }else{
            System.out.println("ERROR ! The Football club list is Full");//if the spaces in the football club drops to zero printing an error message

        }
        System.out.println("\n");

        if (list_of_footballClubs.size()>maxNoClubs) {//if the free slots became greater than or equal to the maximum number of clubs printing an error message
            System.out.println("ERROR ! No spaces available to add any football club");
            System.out.println("\n");
        }
        System.out.println("===========================================================================");
        System.out.println("PLEASE SAVE THE DATA BY PRESSING (6) TO PROCEED WITHOUT ANY PROBLEM !!!");
        System.out.println("===========================================================================");
        System.out.println("\n");
    }

    //Method that is used to delete an existing football club from premier league
    @Override
    public void deleteExistingClub(String clubName) {
        if (list_of_footballClubs.isEmpty()) {//printing an error message if the football club list is empty..so can't perform delete operation
            System.out.println("No Football clubs in the list,yet!");
        } else {
            boolean foundClub = false;//boolean value to find the club name
            for (FootballClub footballClub : list_of_footballClubs) {
                if (footballClub.getClubName().equals(clubName)) {//if the club name is inside the arraylist
                    foundClub = true;//making the boolean value to true
                    list_of_footballClubs.remove(footballClub);//removing the relevant club from the list of football clubs
                    System.out.println("SAD NEWS !!! YOU HAVE SUCCESSFULLY DELETED THE CLUB");
                    System.out.printf("A %s has Left the Football Club List.%n", footballClub instanceof UniversityFootballClub ? "University Football Club" : "School Football Club");
                    System.out.println("\n");
                    //printing the number of free slots remaining
                    System.out.println("Free Slots remaining : "+(maxNoClubs-list_of_footballClubs.size()));
                    System.out.println("\n");
                    System.out.println("===========================================================================");
                    System.out.println("PLEASE SAVE THE DATA BY PRESSING (6) TO PROCEED WITHOUT ANY PROBLEM !!!");
                    System.out.println("===========================================================================");
                    System.out.println("\n");
                    break;
                }
            }

            if (foundClub == false) {//if the club is not found printing an error message
                System.out.println("Invalid Club Name! Please Check & Try Again!");
                System.out.println("\n");
            }
        }
    }

    //Method that is used to display the statistics for a selected club
    @Override
    public void displayStatisticSelectedClub(String clubNameDisplay) throws IOException {

        //clear the arraylists
        list_of_footballClubs.clear();
        playedMatchesSimulation.clear();

        //load from the text file
        try {
            //Creating a stream to read the objects in the text file
            FileInputStream fileInputStream1 = new FileInputStream("footballClubPremierLeague.txt");
            ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);

            while (true) {
                FootballClub footballClub = (FootballClub) objectInputStream1.readObject();

                list_of_footballClubs.add(footballClub);

            }


        } catch (ClassNotFoundException classNotFoundException) {//exception for class not found
            System.out.println("ERROR ! Class not found Exception has occurred");
            System.out.println("\n");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("ERROR ! File not found Exception has occurred");
            System.out.println("\n");
        } catch (EOFException eofException) {//exception for end of file
            System.out.println("==============================");
            System.out.println("FILE HAS BEEN READ COMPLETELY");
            System.out.println("==============================");
            System.out.println("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list_of_footballClubs.size() > 1) {
            System.out.println("DATA LOADED SUCCESSFULLY OF FOOTBALL CLUBS");
            System.out.println("\n");
        }

        try {
            //Creating a stream to read the objects in the text file
            FileInputStream fileInputStream2 = new FileInputStream("matchSimulation.txt");
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);

            while (true) {
                MatchSimulation matchSimulation = (MatchSimulation) objectInputStream2.readObject();

                playedMatchesSimulation.add(matchSimulation);
            }

        } catch (ClassNotFoundException classNotFoundException) {//exception for class not found
            System.out.println("ERROR ! Class not found Exception has occurred");
            System.out.println("\n");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("ERROR ! File not found Exception has occurred");
            System.out.println("\n");
        } catch (EOFException eofException) {//exception for end of file
            System.out.println("==============================");
            System.out.println("FILE HAS BEEN READ COMPLETELY");
            System.out.println("==============================");
            System.out.println("\n");
        }
        if (playedMatchesSimulation.size() > 1) {
            System.out.println("DATA LOADED SUCCESSFULLY OF MATCHES PLAYED");
            System.out.println("\n");
        }


        if (list_of_footballClubs.isEmpty()) {//printing an error message if the football club is empty
            System.out.println("No Football clubs in the list,yet!");
            System.out.println("\n");
        } else {
            boolean foundClub = false;//boolean value to find the club name

            for (FootballClub footballClub : list_of_footballClubs) {
                if (footballClub.getClubName().equals(clubNameDisplay)) {//if the club is in the list of football clubs
                    foundClub = true;//making the boolean value tto true
                    System.out.println("******************************** STATISTICS ********************************");
                    System.out.println("\n");
                    if (footballClub instanceof UniversityFootballClub) {//if the football club is a university football club printing the name of the university
                        System.out.println("* Name of the University [U23 Division] \t: " + ((UniversityFootballClub) footballClub).getUniversityName());
                    } else {
                        //if the football club is a school football club printing the name of the school
                        System.out.println("* Name of the School [U18 Division]\t: " + ((SchoolFootballClub) footballClub).getSchoolName());
                    }
                    System.out.println("* Name of the Club \t\t\t: " + footballClub.getClubName());//displaying the name of the club
                    System.out.println("* Country of the Club \t\t\t: " + footballClub.getCountry());//displaying the country of the club
                    System.out.println("* Location of the Club \t\t\t: " + footballClub.getLocation());//displaying the city of the club
                    System.out.println("* Number Of Matches Played \t\t: " + footballClub.getNoOfMatchesPlayed());//displaying the number of matches played by the club
                    System.out.println("* Number of Matches Won \t\t: " + footballClub.getMatchesWon());//displaying the number of matches won by the club
                    System.out.println("* Number of Matches Lost \t\t: " + footballClub.getMatchesLost());//displaying the number of matches lost by the club
                    System.out.println("* Number of Matches Drawn \t\t: " + footballClub.getMatchesDrawn());//displaying the number of matches drawn by the club
                    System.out.println("* Goals Scored \t\t\t\t: " + footballClub.getGoalsScored());//displaying the number of goals scored by the club
                    System.out.println("* Goals Received \t\t\t: " + footballClub.getGoalsReceived());//displaying the number of goals received by the club
                    System.out.println("* Points Scored \t\t\t: " + footballClub.getPointsScored());//displaying the points scored by the club
                    System.out.println("\n");
                    System.out.println("******************************************************************************");
                    System.out.println("\n");
                    break;
                }
            }
            if (foundClub == false) {//if the football club is not found printing an error message
                System.out.println("Invalid Club Name! Please Check & Try Again!");
                System.out.println("\n");
            }
        }
    }


    //Method that is used to display the premier league table in descending order of their points or goal difference
    @Override
    public void displayPremierLeagueTable() throws IOException {
        Scanner user_input = new Scanner(System.in);

        //clear the arraylists
        list_of_footballClubs.clear();
        playedMatchesSimulation.clear();

        //load from the text file
        try {
            //Creating a stream to read the objects in the text file
            FileInputStream fileInputStream1 = new FileInputStream("footballClubPremierLeague.txt");
            ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);

            while (true) {
                FootballClub footballClub = (FootballClub) objectInputStream1.readObject();

                list_of_footballClubs.add(footballClub);

            }


        } catch (ClassNotFoundException classNotFoundException) {//exception for class not found
            System.out.println("ERROR ! Class not found Exception has occurred");
            System.out.println("\n");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("ERROR ! File not found Exception has occurred");
            System.out.println("\n");
        } catch (EOFException eofException) {//exception for end of file
            System.out.println("==============================");
            System.out.println("FILE HAS BEEN READ COMPLETELY");
            System.out.println("==============================");
            System.out.println("\n");
        }
        if (list_of_footballClubs.size() > 1) {
            System.out.println("DATA LOADED SUCCESSFULLY OF FOOTBALL CLUBS");
            System.out.println("\n");
        }

        try {
            //Creating a stream to read the objects in the text file
            FileInputStream fileInputStream2 = new FileInputStream("matchSimulation.txt");
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);

            while (true) {
                MatchSimulation matchSimulation = (MatchSimulation) objectInputStream2.readObject();

                playedMatchesSimulation.add(matchSimulation);
            }

        } catch (ClassNotFoundException classNotFoundException) {//exception for class not found
            System.out.println("ERROR ! Class not found Exception has occurred");
            System.out.println("\n");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("ERROR ! File not found Exception has occurred");
            System.out.println("\n");
        } catch (EOFException eofException) {//exception for end of file
            System.out.println("==============================");
            System.out.println("FILE HAS BEEN READ COMPLETELY");
            System.out.println("==============================");
            System.out.println("\n");
        }
        if (playedMatchesSimulation.size() > 1) {
            System.out.println("DATA LOADED SUCCESSFULLY OF MATCHES PLAYED");
            System.out.println("\n");
        }


        System.out.println("1 => Display Premier League Table");
        System.out.println("2 => Filter Matches played to a particular date");
        System.out.println("\n");
        System.out.println("Select [1 or 2] from above to proceed... : ");
        int choice = user_input.nextInt();
        System.out.println("\n");

        if (choice == 1) {//if the user wants to show the premier league table
            Collections.sort(list_of_footballClubs, Collections.reverseOrder());//sort the arraylist of football clubs in descending order

            System.out.println("------------------------------------------------------------------------------------------------" +
                    "---------------------------------------------------------------------------------------------------------------");
            //headings of the table
            System.out.printf("| %-22s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-14s |", "ClubName", "Country", "Location",
                    "NoOfMatchesPlayed", "MatchesWon", "MatchesLost", "MatchesDrawn", "GoalsScored", "GoalsReceived", "GoalsDifference", "PointsScored");
            System.out.println("\n");
            System.out.println("------------------------------------------------------------------------------------------------" +
                    "---------------------------------------------------------------------------------------------------------------");

            System.out.println("\n");

            for (FootballClub footballClub : list_of_footballClubs) {
                //values that are coming in the table
                System.out.printf("| %-22s | %-15s | %-15s | %-17s | %-15s | %-15s | %-15s | %-15s | %-15s |%-15s | %-14s |", footballClub.getClubName(), footballClub.getCountry(),
                        footballClub.getLocation(), footballClub.getNoOfMatchesPlayed(), footballClub.getMatchesWon(), footballClub.getMatchesLost(),
                        footballClub.getMatchesDrawn(), footballClub.getGoalsScored(), footballClub.getGoalsReceived(), (footballClub.getGoalsScored() - footballClub.getGoalsReceived()), footballClub.getPointsScored(), "|\n");
                System.out.println("\n");
                System.out.println("------------------------------------------------------------------------------------------------" +
                        "---------------------------------------------------------------------------------------------------------------");
                System.out.println("\n");
            }
        } else if (choice == 2) {//if the user wants to filter the matches played by a specific date
            boolean dateFoundBoolean = false;//boolean vale to find the date of the match played

            System.out.println("Please Enter the Day of the match played: ");//taking the day of the match played
            int day = user_input.nextInt();

            System.out.println("Please Enter the Month of the match played: ");//taking the month of the match played
            int month = user_input.nextInt();

            System.out.println("Please Enter the Year of the match played : 2020");//taking the year of the match played
            int year = 2020;
            System.out.println("\n");

            for (MatchSimulation matchSimulation : playedMatchesSimulation) {//looping inside the match simulation class from the arraylist
                //if the day,month and year is in the arraylist printing the statistics of the matches played
                if ((matchSimulation.getDateOfMatchPlayed().getDay() == day) && (matchSimulation.getDateOfMatchPlayed().getMonth() == month)
                        && (matchSimulation.getDateOfMatchPlayed().getYear() == year)) {
                    System.out.println("-----------------------------------------------------------------------------------------------------");

                    System.out.printf("| %-22s | %-22s | %-15s | %-25s |", "HomeClubName", "OpponentClubName", "HomeClubGoalsScored", "OpponentClubGoalsScored");
                    System.out.println("\n");
                    System.out.println("-----------------------------------------------------------------------------------------------------");
                    System.out.println("\n");

                    //displaying the values of the table which is sorted to a specific date
                    System.out.printf("| %-22s | %-22s | %-19s | %-25s | ", matchSimulation.getHomeTeam(),
                            matchSimulation.getOpponentTeam(), matchSimulation.getGoalsScoredHomeTeam(), matchSimulation.getGoalsScoredOpponentTeam(), "|\n");
                    System.out.println("\n");
                    System.out.println("-----------------------------------------------------------------------------------------------------");
                    System.out.println("\n");
                    dateFoundBoolean = true;//making the boolean value to true as the date is correct
                }

            }
            if (dateFoundBoolean == false) {//if the date is not found printing and error message
                System.out.println("ERROR ! Invalid Date or You have entered a wrong date...");
                System.out.println("\n");
            }
        } else {
            //if the user inputs anything else 1 and 2 options printing an error message
            System.out.println("ERROR ! Wrong Input...Try Again...");
            System.out.println("\n");
        }

    }

    //Method that is used to add a played match with its score and its date
    @Override
    public void addPlayedMatch(String homeTeamPlaying, String opponentTeamPlaying, DateMatchesPlayed dateMatchPlaying,int goalsScoredHomeTeam, int goalsScoredOpponentTeam) {

        //check that home team and the opponent team is equal
        if (homeTeamPlaying.equals(opponentTeamPlaying)) {
            System.out.println("ERROR ! Home Team and Opponent Team cannot be the same");
            System.out.println("\n");
        }

        boolean homeClubFound = false;//to find the home club entered by the user
        boolean opponentClubFound = false;//to find the opponent club entered by the user

        boolean isClubUniversity = false;//to find the club entered by the user belongs to which division

        FootballClub homeClub = null;//taking a variable to set the relevant attributes related to that particular football club(home club)


        for (FootballClub footballClub : list_of_footballClubs) {
            if (footballClub.getClubName().equals(homeTeamPlaying)) {//if the home club entered by the user is in the football club arraylist
                if (footballClub instanceof UniversityFootballClub) {//and of the home club os a university football club
                    isClubUniversity = true;//making the boolean value to true as the home club is a university football club.
                }
                //else if the football club entered by the user is a school football club
                homeClub = footballClub;//take the specific club name entered by the user and the relevant features of that club name into the home club variable

                homeClubFound = true;//as the home club is found making the boolean value to true

            }
        }

        FootballClub opponentClub = null;//taking a variable to set the relevant attributes related to that particular football club(opponent club)


        for (FootballClub footballClub : list_of_footballClubs) {
            if ((footballClub.getClubName().equals(opponentTeamPlaying))) {//if the opponent club entered by the user is in the list of football clubs
                if (isClubUniversity == true) {//making the boolean value to true as it a university football club
                    if (footballClub instanceof UniversityFootballClub) {
                        opponentClub = footballClub;//take the specific club name entered by the user and the relevant features of that club name into the opponentclub 			variable
                        opponentClubFound = true;//making the boolean value to true as the opponent club is found

                    }
                } else {
                    if (footballClub instanceof SchoolFootballClub) {//if the foot ball club entered by the user is a school football club
                        opponentClub = footballClub;//take the specific club name entered by the user and the relevant features of that club name into the opponentclub 				variable

                        opponentClubFound = true;//making the boolean value to true as the opponent club is found

                    }
                }

            }
        }

        if (homeClubFound == false) {//if the home club entered by the user is not found printing an error message
            System.out.println("ERROR ! This Home Team is not registered...Please register it first !!!");
            System.out.println("\n");

        }
        if (opponentClubFound == false) {//if the opponent club entered by the user is not found printing an error message
            System.out.println("ERROR ! This Opponent Team is not registered on selected divisions...Please register it first !!!");
            System.out.println("\n");

        }
        if (homeClubFound == true && opponentClubFound == true) {//if the home club and the opponent club entered by the user, both are found adding the elements to 		the arraylist and setting it to the match simulation class
            MatchSimulation matchSimulation = new MatchSimulation(homeTeamPlaying, opponentTeamPlaying, dateMatchPlaying, goalsScoredHomeTeam, 					goalsScoredOpponentTeam);
            playedMatchesSimulation.add(matchSimulation);
            //System.out.println(playedMatchesSimulation);

            homeClub.setNoOfMatchesPlayed(homeClub.getNoOfMatchesPlayed() + 1);//increase the number of matches played by one
            homeClub.setGoalsScored(homeClub.getGoalsScored() + goalsScoredHomeTeam);//updating the goals scored the home team
            homeClub.setGoalsReceived(homeClub.getGoalsReceived() + goalsScoredOpponentTeam);//updating the goals received by the home team

            opponentClub.setNoOfMatchesPlayed(opponentClub.getNoOfMatchesPlayed() + 1);//increase the number of matches played by one
            opponentClub.setGoalsScored(opponentClub.getGoalsScored() + goalsScoredOpponentTeam);//updating the goals scored the opponent team
            opponentClub.setGoalsReceived(opponentClub.getGoalsReceived() + goalsScoredHomeTeam);//updating the goals received by the opponent team


            if (goalsScoredHomeTeam > goalsScoredOpponentTeam) {//if the goals scored by home team is greater than the goals scored by the opponent team
                homeClub.setPointsScored(homeClub.getPointsScored() + 3);//increasing the points of the home team by 3
                homeClub.setMatchesWon(homeClub.getMatchesWon() + 1);//increasing the number of matches won by the home team by one
                opponentClub.setMatchesLost(opponentClub.getMatchesLost() + 1);//increasing the number of matches lost by the opponent team by one
                System.out.println("HOME CLUB HAS WON THE MATCH...");
                System.out.println("\n");

            }
            if (goalsScoredHomeTeam < goalsScoredOpponentTeam) {//if the goals scored by opponent team is greater than the goals scored by the home team
                opponentClub.setPointsScored(opponentClub.getPointsScored() + 3);//increasing the points of the opponent team by 3
                opponentClub.setMatchesWon(opponentClub.getMatchesWon() + 1);//increasing the number of matches won by the opponent team by one
                homeClub.setMatchesLost(homeClub.getMatchesLost() + 1);//increasing the number of matches lost by the home team by one
                System.out.println("OPPONENT CLUB HAS WON THE MATCH...");
                System.out.println("\n");

            }
            if (goalsScoredHomeTeam == goalsScoredOpponentTeam) {//if the goals scored by the home team and the opponent team is equal
                homeClub.setPointsScored(homeClub.getPointsScored() + 1);//increasing the number of points scored by the home club by one
                opponentClub.setPointsScored(opponentClub.getPointsScored() + 1);//increasing the number og points scored by the opponent club by one
                homeClub.setMatchesDrawn(homeClub.getMatchesDrawn() + 1);//increasing the number of matches drawn by the home club by one
                opponentClub.setMatchesDrawn(opponentClub.getMatchesDrawn() + 1);//increasing the number of matches drawn by the opponent club by one
                homeClub.setNoOfMatchesPlayed(homeClub.getNoOfMatchesPlayed() + 1);//No of matches played by home club increased by one
                opponentClub.setNoOfMatchesPlayed(opponentClub.getNoOfMatchesPlayed() + 1);//No of matches played by opponent club increased by one
                System.out.println("MATCH HAS BEEN DRAWN...");
                System.out.println("\n");
            }
        }
        System.out.println("===========================================================================");
        System.out.println("PLEASE SAVE THE DATA BY PRESSING (6) TO PROCEED WITHOUT ANY PROBLEM !!!");
        System.out.println("===========================================================================");
        System.out.println("\n");
    }

    //Method that is used to save the the information entered by the user into a text file
    @Override
    public void saveInAFile() {

        try {
            //creating text file of football clubs
            FileOutputStream fileOutputStreamPremierLeague1 = new FileOutputStream("footballClubPremierLeague.txt");
            ObjectOutputStream objectOutputStreamPremierLeague1 = new ObjectOutputStream(fileOutputStreamPremierLeague1);

            //creating text file of matches played
            FileOutputStream fileOutputStreamPremierLeague2 = new FileOutputStream("matchSimulation.txt");
            ObjectOutputStream objectOutputStreamPremierLeague2 = new ObjectOutputStream(fileOutputStreamPremierLeague2);

            //writing objects into the text file which are in the football clubs
            for (FootballClub footballClub : list_of_footballClubs) {
                objectOutputStreamPremierLeague1.writeObject(footballClub);
            }
            //flush the object output stream
            objectOutputStreamPremierLeague1.flush();
            //close the fileoutputstream and objectoutputstream
            fileOutputStreamPremierLeague1.close();
            objectOutputStreamPremierLeague1.close();

            //writing objects into the text file which the matches are played
            for (MatchSimulation matchSimulation : playedMatchesSimulation) {
                objectOutputStreamPremierLeague2.writeObject(matchSimulation);
            }
            //flush the object output stream
            objectOutputStreamPremierLeague2.flush();
            //close the fileoutputstream and objectoutputstream
            fileOutputStreamPremierLeague2.close();
            objectOutputStreamPremierLeague2.close();

            System.out.println("DATA SAVED SUCCESSFULLY...");
            System.out.println("\n");
            //show any errors there are errors
        } catch (Exception exception) {
            System.out.println("ERROR in Saving !");
            System.out.println("\n");
        }
    }


    @Override
    public void loadFromFile() throws IOException {

        try {
            //Creating a stream to read the objects in the text file
            FileInputStream fileInputStream1 = new FileInputStream("footballClubPremierLeague.txt");
            ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);

            while (true) {
                FootballClub footballClub = (FootballClub) objectInputStream1.readObject();

                list_of_footballClubs.add(footballClub);

            }


        } catch (ClassNotFoundException classNotFoundException) {//exception for class not found
            System.out.println("ERROR ! Class not found Exception has occurred");
            System.out.println("\n");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("ERROR ! File not found Exception has occurred");
            System.out.println("\n");
        } catch (EOFException eofException) {//exception for end of file
            System.out.println("==============================");
            System.out.println("FILE HAS BEEN READ COMPLETELY");
            System.out.println("==============================");
            System.out.println("\n");
        }
        if (list_of_footballClubs.size() > 1) {
            System.out.println("DATA LOADED SUCCESSFULLY OF FOOTBALL CLUBS");
            System.out.println("\n");
        }

        try {
            //Creating a stream to read the objects in the text file
            FileInputStream fileInputStream2 = new FileInputStream("matchSimulation.txt");
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);

            while (true) {
                MatchSimulation matchSimulation = (MatchSimulation) objectInputStream2.readObject();

                playedMatchesSimulation.add(matchSimulation);
            }

        } catch (ClassNotFoundException classNotFoundException) {//exception for class not found
            System.out.println("ERROR ! Class not found Exception has occurred");
            System.out.println("\n");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("ERROR ! File not found Exception has occurred");
            System.out.println("\n");
        } catch (EOFException eofException) {//exception for end of file
            System.out.println("==============================");
            System.out.println("FILE HAS BEEN READ COMPLETELY");
            System.out.println("==============================");
            System.out.println("\n");
        }
        if (playedMatchesSimulation.size() > 1) {
            System.out.println("DATA LOADED SUCCESSFULLY OF MATCHES PLAYED");
            System.out.println("\n");
        }
    }

}
