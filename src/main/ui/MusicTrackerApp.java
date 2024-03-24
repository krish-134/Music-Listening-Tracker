package ui;

import model.MusicLibrary;
import model.Musician;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This UI class performs all the print statements and considers branches for user's command
// The structuring of this UI is based off of the TellerApp program from CPSC 210 practice problems
public class MusicTrackerApp {
    private static final String JSON_STORE = "./data/musicLibrary.json";

    private Scanner input;
    private MusicLibrary userML;

    private JsonWriter jsonWriter;

    private JsonReader jsonReader;


    // EFFECTS: begins the music tracking application
    public MusicTrackerApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMusicTracker();
    }

    // MODIFIES: this
    // EFFECTS: runs the series of commands for user's input
    private void runMusicTracker() {
        boolean goLoop = true;
        String choice;

        init();

        while (goLoop) {
            mainMenu();
            choice = input.next();

            if (choice.equalsIgnoreCase("5")) {
                askSaveBeforeQuit();
                goLoop = false;
            } else {
                nextCommand(choice);
            }
        }
        System.out.println("See you next time!");
    }

    // MODIFIES: this
    // EFFECTS: initializes a library of music that the user has already heard,
    //          along with the user's name
    private void init() {
        List<Musician> artists = new ArrayList<>();

        userML = new MusicLibrary(artists);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays the initial menu of program options
    private void mainMenu() {
        System.out.println("Select from the options below:");
        System.out.println("\t1 - add new music");
        System.out.println("\t2 - statistics from your music");
        System.out.println("\t3 - load music library from file");
        System.out.println("\t4 - save music library to file");
        System.out.println("\t5 - exit program");
    }

    // EFFECTS: evaluates user decision on adding new music or viewing their info
    public void nextCommand(String choice) {
        switch (choice) {
            case "1":
                new MusicLog(input, userML);
                break;
            case "2":
                if (userML.getMusicians().size() == 0) {
                    System.out.println("--Current music library is empty, no statistics can be made-- \n"
                            + "--Add music or load your music library from file before viewing statistics--\n");
                } else {
                    new ViewStats(input, userML);
                }
                break;
            case "3":
                loadMusicLibrary();
                break;
            case "4":
                saveMusicLibrary();
                break;
            default:
                System.out.println("--Input option does not exist--\n");
                break;
        }
    }

    private void askSaveBeforeQuit() {
        String choice;
        System.out.println("Do you want to save before quitting?");
        System.out.println("\t1 - yes, save my current music library");
        System.out.println("\t2 - no, do not save");
        choice = input.next();
        if (choice.equals("1")) {
            saveMusicLibrary();
        } else if (!choice.equals("2")) {
            System.out.println("--Input option does not exist--\n");
        }
    }

    // EFFECTS: saves the music library to file
    private void saveMusicLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(userML);
            jsonWriter.close();
            System.out.println("Saved current Music Library to: " + JSON_STORE + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadMusicLibrary() {
        try {
            userML = jsonReader.read();
            System.out.println("Loaded music library from " + JSON_STORE + "\n");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE + "\n");
        }
    }


    public MusicLibrary getUserML() {
        return userML;
    }

}