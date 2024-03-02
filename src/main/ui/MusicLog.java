package ui;

import model.MusicLibrary;
import model.Musician;
import model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This class performs all the methods that involve adding new music to the library
public class MusicLog {
    private Scanner input;
    private MusicLibrary userML;

    //EFFECTS: Initializes the input and userML from the initial menu, then performs music entries
    public MusicLog(Scanner input, MusicLibrary userML) {
        this.input = input;
        this.userML = userML;
        addMusic();
    }

    // MODIFIES: this
    // EFFECTS: checks if the artist already exists in the user's log
    //          and performs corresponding command, where a new artist
    //          will be created if it does not currently exist in the library
    private void addMusic() {
        String artistName = null;
        boolean goLoop = true;
        while (goLoop) {
            System.out.println("Enter the artist that made the song:");
            artistName = input.next();

            if (artistName.equals("")) {
                System.out.println("--Artist name cannot be empty string--\n");
            } else {
                goLoop = false;
            }
        }

        if (userML.isMusicianFound(artistName)) {
            addSongInfo(artistName);

        } else {
            addNewMusician(artistName);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the number of times a song has been played if the song already
    //          exists in the user's music library, otherwise creates a new entry for a song
    private void addSongInfo(String artistName) {
        String songName = null;
        boolean goLoop = true;
        while (goLoop) {
            System.out.println("Enter a " + artistName + " song:");
            songName = input.next();
            if (songName.equals("")) {
                System.out.println("--Song name cannot be empty string--\n");
            } else {
                goLoop = false;
            }
        }

        boolean isSongInList = userML.findMusician(artistName).isSongFound(songName);

        if (isSongInList) {
            System.out.println("Enter the amount of times you've played '" + songName + "' since your last log:");
            int timesPlayed = Integer.parseInt(input.next());
            userML.findMusician(artistName).findSong(songName).addTimesPlayed(timesPlayed);
            System.out.println("The number of times you played '" + songName + "' has updated!\n");

        } else {
            Song newSong = enterSongValues(artistName, songName);

            userML.findMusician(artistName).addSong(newSong);

        }
    }

    // MODIFIES: this
    // EFFECTS: takes the input of song time values and assigns to
    //          a returned Song object. The user will be alerted if they
    //          entered 0 for any of the values
    private Song enterSongValues(String artistName, String songName) {
        double songLength = 0.0;
        int timesPlayed = 0;
        boolean goLoop = true;
        while (goLoop) {
            System.out.println("Enter the length of '" + songName + "':");
            songLength = Double.parseDouble(input.next());
            System.out.println("Enter the amount of times you've played '" + songName + "':");
            timesPlayed = Integer.parseInt(input.next());
            if ((songLength == 0) || (timesPlayed == 0)) {
                System.out.println("--Zero is not a viable entry--\n");
            } else {
                goLoop = false;
            }
        }

        System.out.println("'" + songName + "' has been added to the "
                + artistName + " entry!\n");
        return new Song(songName, songLength, timesPlayed);
    }

    // REQUIRES: At least one song should be added to the new musician entry
    // MODIFIES: this
    // EFFECTS: creates a new musician that the user has listened to,
    //          along with the songs the user has heard, otherwise will exit
    //          after the user has added at least one song to the musician
    private void addNewMusician(String artistName) {
        boolean hasAddedSong = false;
        int atLeastOnce = 1;
        while (atLeastOnce > 0) {
            displayAddSongMenu(artistName);
            int addSongStatus = addSongCases(artistName, hasAddedSong);
            if (addSongStatus == 0) {
                atLeastOnce = 0;
            } else if (addSongStatus == 1) {
                hasAddedSong = true;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: performs the cases of adding a song to an artist. At least
    //          one song must be added to the musician before exiting, and
    //          a new artist with a list of songs will be created if required.
    //          True is returned if a song has been added.
    private int addSongCases(String artistName, boolean hasAddedSong) {
        String choice = input.next();

        if (choice.equalsIgnoreCase("2") && hasAddedSong) {
            return 0;
        } else if (choice.equalsIgnoreCase("1")) {
            checkArtistNew(artistName);
            return 1;
        } else if (!hasAddedSong) {
            System.out.println("--Enter at least one song entry--\n");
            return 2;
        } else {
            System.out.println("--Option does not exist--\n");
            return 2;
        }
    }

    // EFFECTS: determines if the artist is new and needs to be added to library, otherwise
    //          will add more song entries to user's new musician entry.
    //          Also takes in the song name to add it to the artist's songs entry
    private void checkArtistNew(String artistName) {
        List<Song> songList;
        if (userML.isMusicianFound(artistName)) {
            songList = userML.findMusician(artistName).getSongs();
        } else {
            songList = new ArrayList<>();
        }

        System.out.println("Add a " + artistName + " song:");
        String songName = input.next();

        if (userML.findMusician(artistName) != null) {
            if (userML.findMusician(artistName).isSongFound(songName)) {
                System.out.println("--Song has already been entered--\n");
            } else {
                userML.findMusician(artistName).addSong(enterSongValues(artistName, songName));
            }
        } else {
            songList.add(enterSongValues(artistName, songName));
            userML.addMusician(new Musician(artistName, songList));
        }
    }

    // EFFECTS: displays the options for adding a song
    private void displayAddSongMenu(String artistName) {
        System.out.println("Select from the options below:");
        System.out.println("\t1 - add a song to the '" + artistName + "' log");
        System.out.println("\t2 - exit from adding songs");
    }
}
