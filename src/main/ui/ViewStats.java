package ui;

import model.MusicLibrary;
import model.Musician;
import model.Song;

import java.util.Scanner;

public class ViewStats {
    private Scanner input;
    private MusicLibrary userML;

    public ViewStats(Scanner input, MusicLibrary userML) {
        this.input = input;
        this.userML = userML;
        getStats();
    }

    // EFFECTS: displays the most and least heard stats for the user,
    //          and then gives further viewing options
    private void getStats() {
        Musician mostHeard = userML.getMostHeardMusician();
        Musician leastHeard = userML.getMostHeardMusician();

        System.out.println(userML.getName() + "'s total listening time: "
                + userML.getTotalTimeListened() + " minutes\n");

        if (mostHeard == leastHeard) {
            System.out.println("Most listened to artist: " + mostHeard.getName() + "\n");
            printArtistStat(mostHeard);

        } else {
            System.out.println("Most listened to artist: " + mostHeard.getName() + "\n");
            printArtistStat(mostHeard);
            System.out.println("Least listened to artist: " + leastHeard.getName() + "\n");
            printArtistStat(leastHeard);
        }
        viewArtists();
    }

    // EFFECTS: displays all the viewable information about how much
    //          the user has listened to a given musician and their songs
    private void printArtistStat(Musician m) {
        Song mostHeard = m.getMostHeardSong();
        Song leastHeard = m.getLeastHeardSong();
        System.out.println("Artist: " + m.getName());
        System.out.println("Total time spent listening: " + m.getTotalTimeListened() + " minutes");
        System.out.println("Most listened song: '" + mostHeard.getName()
                + "' for a total of " + mostHeard.getTotalTimeListened() + " minutes");
        System.out.println("Least listened song: '" + leastHeard.getName()
                + "' for a total of " + leastHeard.getTotalTimeListened() + " minutes");

        System.out.println("ALl the " + m.getName() + " songs you have listened to include:\n");

        for (Song s: m.getSongsHeard()) {
            System.out.println("Song: " + s.getName());
            System.out.println("Song-length: " + s.getSongLength() + " minutes");
            System.out.println("Times played: " + s.getTimesPlayed() + " times");
            System.out.println("Time listened: " + s.getTotalTimeListened() + " minutes\n");
        }
    }

    // EFFECTS: provides a display of every musician in the user's library
    private void viewArtists() {
        int numArtists = userML.getMusiciansHeard().size();
        System.out.println("You have listened to " + numArtists + " musician(s)!");
        System.out.println("A list of all of your musicians include:\n");

        for (Musician m: userML.getMusiciansHeard()) {
            System.out.println(m.getName());
        }
        System.out.println();

        viewMore();
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
                System.out.println("Input option does not exist");
            }
        }
    }

    // EFFECTS: displays the options for viewing artists
    private void viewArtistsMenu() {
        System.out.println("Select from the options below:");
        System.out.println("\t1 - view the information from an artist");
        System.out.println("\t2 - exit from stats");
    }

    // REQUIRES: the entered musician should appear in the user's music library
    // EFFECTS: displays the information about the requested artist
    private void viewChoiceArtist() {
        String artistName;
        System.out.println("Enter the artist whose information you wish to view:");
        artistName = input.next();

        if (userML.isMusicianFound(artistName)) {
            printArtistStat(userML.findMusician(artistName));
        } else {
            System.out.println("This artist name is not in your music log\n");
        }
    }

}
