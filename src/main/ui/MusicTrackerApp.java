package ui;

import model.MusicLibrary;
import model.Musician;
import model.Song;
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


    // EFFECTS: begins the music tracking application
    public MusicTrackerApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
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

            if (choice.equalsIgnoreCase("4")) {
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
        List<Musician> artists;

        List<Song> kenSongs = new ArrayList<>();
        Song mt = new Song("Money Trees", 6.27, 6);
        kenSongs.add(mt);
        Musician kendrick = new Musician("Kendrick Lamar", kenSongs);
        artists = new ArrayList<>();
        artists.add(kendrick);

        userML = new MusicLibrary(artists);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays the initial menu of program options
    private void mainMenu() {
        System.out.println("Select from the options below:");
        System.out.println("\t1 - add new music");
        System.out.println("\t2 - statistics from your music");
        System.out.println("\t3 - save music library to file");
        System.out.println("\t4 - exit program");
    }

    // EFFECTS: evaluates user decision on adding new music or viewing their info
    private void nextCommand(String choice) {
        if (choice.equals("1")) {
            new LogMusic(input, userML);
        } else if (choice.equals("2")) {
            new ViewStats(input, userML);
        } else if (choice.equals("3")) {
            saveMusicLibrary();
        } else {
            System.out.println("--Input option does not exist--\n");
        }
    }

    // EFFECTS: saves the music library to file
    private void saveMusicLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(userML);
            jsonWriter.close();
            System.out.println("Saved current Music Library to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


}