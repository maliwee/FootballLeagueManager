package controllers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class ConsoleSystem implements Serializable {
    static LeagueManager premierLeagueManager = new PremierLeagueManager();
    final static Scanner User_Input = new Scanner(System.in);//scanner for the user inputs

    public static void main(String[] args) throws IOException{

        try {
            // Sleep for 5 Seconds
            System.out.println("\n");
            System.out.println("......................................................");
            System.out.println("YOU ARE ENTERING TO THE PREMIER LEAGUE");
            System.out.println("\n");
            System.out.println("SERVER IS GETTING READY... !!! PLEASE WAIT ...");
            System.out.println("......................................................");
            System.out.println("\n");
            Thread.sleep(5000);
            System.out.println("*******************HERE WE GO*********************");
            System.out.println("\n");
        }catch(InterruptedException interruptedException) {
            System.out.println(interruptedException);
        }

        //run the playframework and angular in two cmds at the start of the premier league championship
        ProcessBuilder processBuilderPlayFrameWork=new ProcessBuilder();
        processBuilderPlayFrameWork.command("cmd.exe","/c","start sbt run");
        processBuilderPlayFrameWork.directory(new File("../premier-league-manager"));

        ProcessBuilder processBuilderAngular=new ProcessBuilder();
        processBuilderAngular.command("cmd.exe","/c","start ng serve");
        processBuilderAngular.directory(new File("../premier-league-manager-frontend"));

        try {
            //start the cmds to run the playframework and angular projects
            processBuilderPlayFrameWork.start();
            processBuilderAngular.start();
        }catch (Exception exception){
            System.out.println(exception);
        }

        premierLeagueManager.loadFromFile();//load from the file

        mainMenu:
        while (true) {

            displayMenu();//display the menu

            System.out.println("Enter a number from above to Proceed ...");//ask the user to decide a choice from the menu
            while (!User_Input.hasNextInt()) {
                System.out.println("Enter a number to proceed : ");//ask the user to choose a number
                User_Input.next();
                System.out.println("\n");
            }
            int choice=User_Input.nextInt();

            switch (choice) {
                case 1:
                    addToPremierLeague();//call the method to add a new football club
                    break;
                case 2:
                    deleteExistingClub();//call the method to delete an existing club
                    break;
                case 3:
                    displayStatisticSelectedClub();//call the method to display statistics of a particular club
                    break;
                case 4:
                    displayPremierLeagueTable();//call the method to display the premier league table
                    break;
                case 5:
                    addPlayedMatch();//call the method to add a played match
                    break;
                case 6:
                    saveInAFile();//call the method to save the details in text file
                    break;
                case 7:
                    premierLeagueGUI();//call the method to open the premier league gui
                    break;
                case 8:
                    System.out.println("Thank you for choosing the system, Have a pleasant Day");//Exit from the menu
                    break mainMenu;
                default:
                    System.out.println("<<<<You selected an Invalid option. Please Try Again !>>>>");//invalid option selected from the menu
                    continue mainMenu;
            }

        }

    }


    private static void displayMenu() {
        //Display the menu

        System.out.println("------------------------------------------/*\\-------------------------------------------");
        System.out.println("=================WELCOME TO THE FOOTBALL PREMIERE LEAGUE CHAMPIONSHIP=================");
        System.out.println("\n");
        System.out.println("......................................................");
        System.out.println("1. Add a club to the premier League Manager");
        System.out.println("2. Delete an existing club from the premier League");
        System.out.println("3. Display Statistics for a selected club");
        System.out.println("4. Display Premier League Table");
        System.out.println("5. Add a played match");
        System.out.println("6. Save Into a File");
        System.out.println("7. Open Premier League GUI");
        System.out.println("8. Exit");
        System.out.println("......................................................");
        System.out.println("------------------------------------------\\*/------------------------------------------");

        System.out.println("\n");

    }

    //method to add a new club to the premier league
    private static void addToPremierLeague() {

        FootballClub footballClub;//initializing the football club

        //initializing the variables
        int totalMatchesPlayed = 0;
        int noOfMatchesWon = 0;
        int noOfMatchesLost = 0;
        int noOfMatchesDraw = 0;
        int goalsScored = 0;
        int goalsReceived = 0;
        int pointsScored = 0;

        User_Input.nextLine();//as a football club can have spaces between the name of the club,here used a nextLine(), if this nextline() is not there the name
                                //of the club will not be taken, it will skip to take the country of the club.

        mainLoopAdd:
        while (true) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("\t\tYOU ARE GOING TO ADD FOOTBALL CLUBS TO THE PREMIER LEAGUE BASED ON YOUR UNIVERSITY AND SCHOOL");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("\n");

            //enter the name of the football club
            System.out.println("Enter the name of the Club : ");
            String clubName = User_Input.nextLine().toLowerCase();
            System.out.println("\n");

            //if the user enters only a space to the club name will take the club name again
            while (clubName.equals("")) {
                System.out.println("ERROR ! Enter clubName Again : ");
                clubName = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            //if the user enters another character except strings will take the club name again
            while (!clubName.matches("[a-zA-Z]+\\s?[a-zA-Z]+\\s?[a-zA-Z]*$")) {
                System.out.println("Enter a String value for the Club Name...Enter the name of the Club : ");
                clubName = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            //enter the name of the country of the club
            System.out.println("Enter the country of the Club : ");
            String country = User_Input.nextLine().toLowerCase();
            System.out.println("\n");

            //if the user enters a space to the country of the club, country will be taken again
            while (country.equals("")) {
                System.out.println("ERROR ! Enter Country Again : ");
                country = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            //if the user enters any other character except a string, country will be taken again as a user input
            while (!country.matches("[a-zA-Z]+\\s?[a-zA-Z]+\\s?[a-zA-Z]*$")) {
                System.out.println("Enter a String value for the Country...Enter the name of the Club : ");
                country = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            //taking the location of the club
            System.out.println("Enter the location(city) of the Club : ");
            String location = User_Input.nextLine().toLowerCase();
            System.out.println("\n");

            //if the user enters a space to the location,taking the location again
            while (location.equals("")) {
                System.out.println("ERROR ! Enter Location Again : ");
                location = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            //if the user enters any other character except a string to the location, taking the location again
            while (!location.matches("[a-zA-Z]+\\s?[a-zA-Z]+\\s?[a-zA-Z]*$")) {
                System.out.println("Enter a String value for the City of the club...Enter the name of the Club : ");
                location = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            //choice what division the club should play
            footballClubChoice:
            while (true) {
                System.out.println("1. (U23 players) => University Players");//U23 for University players
                System.out.println("2. (U18 players) => School Players");//U18 for school players
                System.out.println("\n");

                //choice of the division
                System.out.println("Do you want to proceed with University Football club or School Football club [Enter the number only (1 or 2)] : ");
                int footballClubChoice = User_Input.nextInt();
                System.out.println("\n");

                if (footballClubChoice == 1) {//user chosen university as the division
                    System.out.println("<<<You have chosen UNIVERSITY FOOTBALL CLUB>>>");
                    System.out.println("\n");

                    User_Input.nextLine();//prevent of skipping the name of the university to the number of players
                    System.out.println("Enter the name of the UNIVERSITY : ");//name of the university
                    String universityName = User_Input.nextLine().toLowerCase();
                    System.out.println("\n");

                    //if the user enters space to the university name,taking the user input again
                    while (universityName.equals("")) {
                        System.out.println("ERROR ! Enter University Name Again...");
                        universityName = User_Input.nextLine().toLowerCase();
                        System.out.println("\n");
                    }

                    //if the user enters any other character except a string to the university name, taking it again
                    while (!universityName.matches("[a-zA-Z]+\\s?[a-zA-Z]+\\s?[a-zA-Z]*$")) {
                        System.out.println("Enter a String value for the University Name...Enter the name of the Club : ");
                        universityName = User_Input.nextLine().toLowerCase();
                        System.out.println("\n");
                    }

                    //enter the number of players in the university, with the reserved players
                    System.out.println("Enter the number of players in the university with reserved played [total number of players] : ");
                    int universityNoPlayers = User_Input.nextInt();
                    System.out.println("\n");

                    //if the user enters the number of players which is less than 0, enter an error message
                    if (universityNoPlayers < 0) {
                        System.out.println("ERROR ! No of players can't be a negative value...");
                        System.out.println("\n");
                        continue footballClubChoice;
                    }

                    //set the values to the university division
                    footballClub = new UniversityFootballClub(universityName, universityNoPlayers, clubName, country,
                            location, totalMatchesPlayed, noOfMatchesWon, noOfMatchesLost, noOfMatchesDraw
                            , goalsScored, goalsReceived, pointsScored);

                    premierLeagueManager.addToPremierLeague(footballClub);//call the add method from the premier league manager

                    break mainLoopAdd;//break the loop after setting the values to the university division

                } else if (footballClubChoice == 2) {//if the chosen the school division
                    System.out.println("<<<You have chosen SCHOOL FOOTBALL CLUB>>>");
                    System.out.println("\n");

                    User_Input.nextLine();
                    System.out.println("Enter the name of the SCHOOL : ");//take the name of the school
                    String schoolName = User_Input.nextLine().toLowerCase();
                    System.out.println("\n");

                    //if the user enters a space to the school name, taking it again
                    while (schoolName.equals("")) {
                        System.out.println("ERROR ! Enter School Name Again : ");
                        schoolName = User_Input.nextLine().toLowerCase();
                        System.out.println("\n");
                    }

                    //if the user enters any other character except a string, taking the school name again
                    while (!schoolName.matches("[a-zA-Z]+\\s?[a-zA-Z]+\\s?[a-zA-Z]*$")) {
                        System.out.println("Enter a String value for the School Name...Enter the name of the Club : ");
                        schoolName = User_Input.nextLine().toLowerCase();
                        System.out.println("\n");
                    }

                    //enter the number of players in the school
                    System.out.println("Enter the number of players in the school with reserved played [total number of players]  : ");
                    int schoolNoPlayers = User_Input.nextInt();
                    System.out.println("\n");

                    //if the user enters the number of players less than 0, printing an error message
                    if (schoolNoPlayers < 0) {
                        System.out.println("No of players can't be a negative value...");
                        System.out.println("\n");
                        continue footballClubChoice;
                    }

                    //setting the school football club after getting the values
                    footballClub = new SchoolFootballClub(schoolName, schoolNoPlayers, clubName, country,
                            location, totalMatchesPlayed, noOfMatchesWon, noOfMatchesLost, noOfMatchesDraw,
                            goalsScored, goalsReceived, pointsScored);

                    premierLeagueManager.addToPremierLeague(footballClub);//calling the add to premier league method

                    break mainLoopAdd;//break the loop after setting the values to the school division
                } else {
                    //printing an error message if the user selects any other number in the division selection
                    System.out.println("In this age you can't play football any of the age category mentioned above");
                    System.out.println("\n");
                    break mainLoopAdd;
                }
            }
        }
    }


    private static void deleteExistingClub() {

        User_Input.nextLine();
        mainLoopDelete:
        while (true) {
            System.out.println("Enter the CLUB NAME you want to delete : ");//enter the name of the club to be deleted
            String deleteClub = User_Input.nextLine().toLowerCase();
            System.out.println("\n");

            //if the user enters a space to the name of the club, taking the name of the club again
            while (deleteClub.equals("")) {
                System.out.println("ERROR ! Enter Club Name Again : ");
                deleteClub = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            //if the user enters any other character except string, taking the club name again
            while (!deleteClub.matches("[a-zA-Z]+\\s?[a-zA-Z]+\\s?[a-zA-Z]*$")) {
                System.out.println("Enter a String value for the Club Name...Enter the name of the Club : ");
                deleteClub = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            premierLeagueManager.deleteExistingClub(deleteClub);//calling the delete method again
            break mainLoopDelete;
        }

    }

    private static void displayStatisticSelectedClub() throws IOException {

        User_Input.nextLine();
        mainLoopDisplayStats:
        while (true) {
            System.out.println("Enter the name of the Football Club : ");//taking thee name of the football club to display the stattistics
            String clubNameDisplay = User_Input.nextLine().toLowerCase();
            System.out.println("\n");

            //if the user enters a space to the club name, taking the club name again
            while (clubNameDisplay.equals("")) {
                System.out.println("ERROR ! Enter Club Name Again : ");
                clubNameDisplay = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            //if the user enters any other character except string, taking the name again
            while (!clubNameDisplay.matches("[a-zA-Z]+\\s?[a-zA-Z]+\\s?[a-zA-Z]*$")) {
                System.out.println("Enter a String value for the Club Name...Enter the name of the Club : ");
                clubNameDisplay = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            premierLeagueManager.displayStatisticSelectedClub(clubNameDisplay);//calling the display statistics methods from the premier league
            break mainLoopDisplayStats;
        }

    }

    private static void displayPremierLeagueTable() throws IOException {

        premierLeagueManager.displayPremierLeagueTable();//calling the premier league table from the premier league manager
    }

    private static void addPlayedMatch() {
        mainLoopAddPlayedMatch:
        while (true) {
            User_Input.nextLine();
            System.out.println("Enter home team playing the premier league [Club name] : ");//taking the name of the home team to play a match
            String homeTeamPlaying = User_Input.nextLine().toLowerCase();
            System.out.println("\n");

            //if the user enters a space to the name of the home team, take the home team again
            while (homeTeamPlaying.equals("")) {
                System.out.println("ERROR ! Enter Home Club Again : ");
                homeTeamPlaying = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            //if the user enters a character except a string,take the home team again
            while (!homeTeamPlaying.matches("[a-zA-Z]+\\s?[a-zA-Z]+\\s?[a-zA-Z]*$")) {
                System.out.println("Enter a String value for the Home Team...Enter the name of the Club : ");
                homeTeamPlaying = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            //taking the name of the opponent team
            System.out.println("Enter the opponent team playing the premier league [Club name]: ");
            String opponentTeamPlaying = User_Input.nextLine().toLowerCase();
            System.out.println("\n");

            //if the user enters a space to the name of the opponent team, take the opponent team again
            while (opponentTeamPlaying.equals("")) {
                System.out.println("ERROR ! Enter Opponent Team Again : ");
                opponentTeamPlaying = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            //if the user enters a character except a string,take the opponent team again
            while (!opponentTeamPlaying.matches("[a-zA-Z]+\\s?[a-zA-Z]+\\s?[a-zA-Z]*$")) {
                System.out.println("Enter a String value for the Opponent Team...Enter the name of the Club : ");
                opponentTeamPlaying = User_Input.nextLine().toLowerCase();
                System.out.println("\n");
            }

            //take the day of the match played
            System.out.println("Please Enter the Day of the match played: ");
            int day = User_Input.nextInt();

            //taking the month of the match played
            System.out.println("Please Enter the Month of the match played: ");
            int month = User_Input.nextInt();

            //year of the match played
            System.out.println("Year of the match played : 2020");
           int year=2020;
            System.out.println("\n");

            System.out.println("Enter the number of goals scored by the Home Team: ");//goals scored by the home team
            int goalsScoredHomeTeam = User_Input.nextInt();
            System.out.println("\n");

            //if the user enters the goals scored by the home team as less than zero, printing an error message
            if (goalsScoredHomeTeam < 0) {
                System.out.println("ERROR ! Goals Scored by the home team can't be a negative value...Re-Enter it again : ");
                System.out.println("\n");
                continue mainLoopAddPlayedMatch;
            }

            //taking the goals scored by the opponent team
            System.out.println("Enter the number of goals scored by the Opponent Team : ");
            int goalsScoredOpponentTeam = User_Input.nextInt();
            System.out.println("\n");

            //if the user enters the goals scored by the opponent team as less than zero, printing an error message
            if (goalsScoredOpponentTeam < 0) {
                System.out.println("ERROR ! Goals Scored by the opponent team can't be a negative value...Re-Enter it again : ");
                System.out.println("\n");
                continue mainLoopAddPlayedMatch;
            }


            //setting the the date to the date constructor
            DateMatchesPlayed dateMatchPlaying = new DateMatchesPlayed(day, month, year);

            //calling the add played match from the premier league
            premierLeagueManager.addPlayedMatch(homeTeamPlaying, opponentTeamPlaying, dateMatchPlaying, goalsScoredHomeTeam, goalsScoredOpponentTeam);
            break mainLoopAddPlayedMatch;
        }
    }


    private static void saveInAFile()  {
        premierLeagueManager.saveInAFile();//calling the saving method from the premier league manager
    }


    private static void premierLeagueGUI(){

        //open localhost:9000 and localhost:4200
        ProcessBuilder processBuilderPlayFramework=new ProcessBuilder();
        processBuilderPlayFramework.command("cmd.exe","/c","start microsoft-edge:http://localhost:9000");

        ProcessBuilder processBuilderAngular=new ProcessBuilder();
        processBuilderAngular.command("cmd.exe","/c","start microsoft-edge:http://localhost:4200");

        try {
            //start the cmds to run the playframework and angular projects
            processBuilderPlayFramework.start();
            processBuilderAngular.start();
        }catch (Exception exception){
            System.out.println(exception);
        }

    }



}