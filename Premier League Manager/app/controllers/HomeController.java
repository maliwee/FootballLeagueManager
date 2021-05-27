package controllers;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HomeController extends Controller {

    //convert football club arraylist to json format
    public Result getFootballClubsToJson() {
        List<FootballClub> footballClubs = footballClubs_readFromFile();

        JsonNode jsonFootballClubs = Json.toJson(footballClubs);

        return ok(jsonFootballClubs);


    }

    private List<FootballClub> footballClubs_readFromFile() {
        List<FootballClub> list_of_footballClubs = new ArrayList<>();

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
        Collections.sort(list_of_footballClubs,Collections.reverseOrder());
        return list_of_footballClubs;
    }

    /*Match Simulation*/

    //convert matchsimulation arraylist to json
    public Result getMatchesPlayedToJson() {
        List<MatchSimulation> matchSimulation = matchesPlayed_readFromFile();

        JsonNode jsonMatchesPlayed = Json.toJson(matchSimulation);

        return ok(jsonMatchesPlayed);


    }

    private List<MatchSimulation> matchesPlayed_readFromFile() {
        List<MatchSimulation> playedMatchSimulation = new ArrayList<>();

        try {
            //Creating a stream to read the objects in the text file
            FileInputStream fileInputStream = new FileInputStream("matchSimulation.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            //read to end of the file and add to the arraylist
            while (true) {
                MatchSimulation matchSimulation = (MatchSimulation) objectInputStream.readObject();

                playedMatchSimulation.add(matchSimulation);
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
        return playedMatchSimulation;
    }
}
