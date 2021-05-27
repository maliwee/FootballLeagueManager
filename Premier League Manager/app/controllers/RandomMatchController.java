package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomMatchController extends Controller {

    //convert randommatch arraylist to json
    public Result getRandomMatchToJson() {
        List<MatchSimulation> randomMatches = randomMatches_readFromFile();

        JsonNode jsonRandomMatchSimulation = Json.toJson(randomMatches);

        return ok(jsonRandomMatchSimulation);


    }

    private List<MatchSimulation> randomMatches_readFromFile() {
        List<FootballClub> list_of_footballClubs = new ArrayList<>();
        List<MatchSimulation> randomMatches = new ArrayList<>();

        try {
            //Creating a stream to read the objects in the text file
            FileInputStream fileInputStream1 = new FileInputStream("footballClubPremierLeague.txt");
            ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);

            //read to end of the file and add to the arraylist
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
            System.out.println("ERROR ! End of File Exception has occurred");
            System.out.println("\n");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        //random home team and opponent team
        Random randomHomeTeam = new Random();
        Random randomOpponentTeam = new Random();

        //random date for random match
        Random date=new Random();

        //random goals score by the home team
        int goalsScoredHomeTeam = randomHomeTeam.nextInt(11);
        //random goals scored by the opponent team
        int goalsScoredOpponentTeam = randomOpponentTeam.nextInt(11);

        //generate random dates
        int dayRandom= date.nextInt(31)+1;
        int monthRandom=date.nextInt(12)+1;
        int yearRandom=2020;

        //set the random dates for the date constructor
        DateMatchesPlayed dateMatchesPlayed =new DateMatchesPlayed(dayRandom,monthRandom,yearRandom);

        clubSameLoop:
        while (true) {

            //generate random home team name
            int randomGenerateHomeTeam = randomHomeTeam.nextInt(list_of_footballClubs.size());
            FootballClub randomElementHomeTeam = list_of_footballClubs.get(randomGenerateHomeTeam);

            //generate random opponent team name
            int randomGenerateOpponentTeam = randomOpponentTeam.nextInt(list_of_footballClubs.size());
            FootballClub randomElementOpponentTeam = list_of_footballClubs.get(randomGenerateOpponentTeam);

            //random home team should be equal to the home team in the football club list
            if (!(randomElementHomeTeam.getClubName().equals(randomElementOpponentTeam.getClubName()))) {
                //check whether the random home team and opponent team is university sports club
                if ((randomElementHomeTeam instanceof UniversityFootballClub && randomElementOpponentTeam instanceof UniversityFootballClub) ||
                        //check whether the random home team and opponent team is school sports club
                        randomElementHomeTeam instanceof SchoolFootballClub && randomElementOpponentTeam instanceof SchoolFootballClub) {


                    //set the values to the match simulation constructor
                    MatchSimulation matchSimulation=new MatchSimulation(randomElementHomeTeam.getClubName(),randomElementOpponentTeam.getClubName(), dateMatchesPlayed,goalsScoredHomeTeam,goalsScoredOpponentTeam);
                    randomMatches.add(matchSimulation);
                    //System.out.println(randomMatches);

                    boolean homeClubFound = false;//to find the home club entered by the user
                    boolean opponentClubFound = false;//to find the opponent club entered by the user

                    boolean isClubUniversity = false;//to find the club entered by the user belongs to which division

                    FootballClub homeClub = null;//taking a variable to set the relevant attributes related to that particular football club(home club)


                    for (FootballClub footballClub : list_of_footballClubs) {
                        if (footballClub.getClubName().equals(randomElementHomeTeam.getClubName())) {//if the home club entered by the user is in the football club arraylist
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
                        if ((footballClub.getClubName().equals(randomElementOpponentTeam.getClubName()))) {//if the opponent club entered by the user is in the list of football clubs
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

                    if (homeClubFound == true && opponentClubFound == true) {//if the home club and the opponent club entered by the user, both are found adding the elements to the arraylist and setting it to the match simulation class

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


                        }
                        if (goalsScoredHomeTeam < goalsScoredOpponentTeam) {//if the goals scored by opponent team is greater than the goals scored by the home team
                            opponentClub.setPointsScored(opponentClub.getPointsScored() + 3);//increasing the points of the opponent team by 3
                            opponentClub.setMatchesWon(opponentClub.getMatchesWon() + 1);//increasing the number of matches won by the opponent team by one
                            homeClub.setMatchesLost(homeClub.getMatchesLost() + 1);//increasing the number of matches lost by the home team by one

                        }
                        if (goalsScoredHomeTeam == goalsScoredOpponentTeam) {//if the goals scored by the home team and the opponent team is equal
                            homeClub.setPointsScored(homeClub.getPointsScored() + 1);//increasing the number of points scored by the home club by one
                            opponentClub.setPointsScored(opponentClub.getPointsScored() + 1);//increasing the number og points scored by the opponent club by one
                            homeClub.setMatchesDrawn(homeClub.getMatchesDrawn() + 1);//increasing the number of matches drawn by the home club by one
                            opponentClub.setMatchesDrawn(opponentClub.getMatchesDrawn() + 1);//increasing the number of matches drawn by the opponent club by one

                        }
                    }


                    break clubSameLoop;
                } else {
                    //continue the loop until the home team and the opponent team is not equal
                    continue clubSameLoop;
                }
            } else {
                //continue the loop until the home team and the opponent team is in the same division to play the match
                continue clubSameLoop;
            }

        }
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
            for (MatchSimulation matchSimulation : randomMatches) {
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
        return randomMatches;
    }
}
