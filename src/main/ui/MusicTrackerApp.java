package ui;

import model.MusicLibrary;
import model.Musician;
import model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This UI class performs all the print statements and considers branches for user's command
// The structuring of this UI is based off of the TellerApp program from CPSC 210 practice problems
public class MusicTrackerApp {

    private Scanner input;
    private MusicLibrary userML;


    // EFFECTS: begins the music tracking application
    public MusicTrackerApp() {
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

            if (choice.equalsIgnoreCase("3")) {
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
        System.out.println("\t3 - exit program");
    }

    // EFFECTS: evaluates user decision on adding new music or viewing their info
    private void nextCommand(String choice) {
        if (choice.equalsIgnoreCase("1")) {
            new LogMusic(input, userML);
        } else if (choice.equalsIgnoreCase("2")) {
            new ViewStats(input, userML);
        } else {
            System.out.println("--Input option does not exist--\n");
        }
    }

}