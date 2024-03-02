package ui;

import model.MusicLibrary;
import model.Musician;
import model.Song;

import java.util.Scanner;

// This class shows the user's behaviour by displaying data from their music library
public class ViewStats {
    private Scanner input;
    private MusicLibrary userML;

    public ViewStats(Scanner input, MusicLibrary userML) {
        this.input = input;
        this.userML = userML;
        getStats();
    }

    // EFFECTS: displays the most and least heard stats for the user,
    //          and then gives further viewing options. If they mostHeard
    //          and leastHeard musician are the same then print one of them, otherwise both
    private void getStats() {
        Musician mostHeard = userML.getMostHeardMusician();
        Musician leastHeard = userML.getLeastHeardMusician();

        System.out.println("Your total listening time: "
                + userML.getTotalTimeListened() + " minutes\n");

        viewArtists();

        if (mostHeard == leastHeard) {
            System.out.println("You have -most- listened too...\n");
            printArtistStat(mostHeard);

        } else {
            System.out.println("You have -most- listened too...\n");
            printArtistStat(mostHeard);
            System.out.println("You have -least- listened too...\n");
            printArtistStat(leastHeard);
        }

        viewMore();
    }

    // EFFECTS: displays all the viewable information about how much
    //          the user has listened to a given musician and their songs.
    //          If the most and least listened to songs are they same only
    //          one is printed, otherwise both are
    private void printArtistStat(Musician m) {
        Song mostHeard = m.getMostHeardSong();
        Song leastHeard = m.getLeastHeardSong();
        System.out.println("Artist: " + m.getName());
        System.out.println("Total time spent listening: " + m.getTotalTimeListened() + " minutes");

        if (mostHeard.getName().equals(leastHeard.getName())) {
            System.out.println("Most listened song: '" + mostHeard.getName()
                    + "' for a total of " + mostHeard.getTotalTimeListened() + " minutes");
        } else {
            System.out.println("Most listened song: '" + mostHeard.getName()
                    + "' for a total of " + mostHeard.getTotalTimeListened() + " minutes");
            System.out.println("Least listened song: '" + leastHeard.getName()
                    + "' for a total of " + leastHeard.getTotalTimeListened() + " minutes");
        }
        displayArtistSongs(m);
    }

    // EFFECTS: displays every song and related song-info from the musician in the music library
    private void displayArtistSongs(Musician m) {
        System.out.println("ALl the " + m.getName() + " songs you have listened to include:\n");
        for (Song s: m.getSongs()) {
            System.out.println("Song: " + s.getName());
            System.out.println("Song-length: " + s.getSongLength() + " minutes");
            System.out.println("Times played: " + s.getTimesPlayed() + " times");
            System.out.println("Time listened: " + s.getTotalTimeListened() + " minutes\n");
        }
    }

    // EFFECTS: provides a display of every musician in the user's library
    private void viewArtists() {
        int numArtists = userML.getMusicians().size();
        System.out.println("You have listened to " + numArtists + " musician(s)!");
        System.out.println("A list of all of your musicians include:\n");

        for (Musician m: userML.getMusicians()) {
            System.out.println(m.getName());
        }

        System.out.println();
    }

    // EFFECTS: gives the option to view stats about any specific musician
    private void viewMore() {
        String choice;
        boolean addMoreSongs = true;

        while (addMoreSongs) {
            viewArtistsMenu();
            choice = input.next();
            if (choice.equalsIgnoreCase("2")) {
                addMoreSongs = false;
            } else if (choice.equalsIgnoreCase("1")) {
                viewChoiceArtist();
            } else {
                System.out.println("--Input option does not exist--\n");
            }
        }
    }

    // EFFECTS: displays the options for viewing artists
    private void viewArtistsMenu() {
        System.out.println("Select more statistics options below:");
        System.out.println("\t1 - view artist information");
        System.out.println("\t2 - exit from stats");
    }

    // EFFECTS: displays the information about the requested artist
    private void viewChoiceArtist() {
        String artistName = null;
        boolean goLoop = true;
        while (goLoop) {
            System.out.println("Enter the artist whose information you wish to view:");
            artistName = input.next();
            if (artistName.equals("")) {
                System.out.println("--Artist name cannot be empty string--\n");
            } else {
                goLoop = false;
            }
        }

        if (userML.isMusicianFound(artistName)) {
            printArtistStat(userML.findMusician(artistName));
        } else {
            System.out.println("--This artist was not found in your music log--\n");
        }
    }

}
