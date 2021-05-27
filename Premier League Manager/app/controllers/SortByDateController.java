package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortByDateController extends Controller {

    //convert the match simulation array to json
    public Result getSortByDateToJson() {
        List<MatchSimulation> matchSimulations = matchesPlayed_readFromFile();

        JsonNode jsonRandomMatchSimulation = Json.toJson(matchSimulations);

        return ok(jsonRandomMatchSimulation);


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
        Collections.sort(playedMatchSimulation);
        return playedMatchSimulation;
    }

}
