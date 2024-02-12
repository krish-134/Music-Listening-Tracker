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
    public void runMusicTracker() {
        boolean goLoop = true;
        String choice;

        init();

        while (goLoop) {
            mainMenu();
            choice = input.next();

            if (choice.equalsIgnoreCase("e")) {
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
    public void init() {
        List<Musician> artists;

        List<Song> kenSongs = new ArrayList<>();
        Song mt = new Song("Money Trees", 6.27, 6);
        kenSongs.add(mt);
        Musician kendrick = new Musician("Kendrick Lamar", kenSongs);
        artists = new ArrayList<>();
        artists.add(kendrick);

        userML = new MusicLibrary("Johny", artists);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays the initial menu of program options
    public void mainMenu() {
        System.out.println("Select from the options below:");
        System.out.println("\ta -> add song to a current or new musician");
        System.out.println("\ts -> statistics from your music listening habits");
        System.out.println("\te -> exit program");
    }

    // EFFECTS: evaluates user decision on adding new music or viewing their info
    public void nextCommand(String choice) {
        if (choice.equalsIgnoreCase("a")) {
            addMusic();
        } else if (choice.equalsIgnoreCase("s")) {
            getStats();
        } else {
            System.out.println("Input option does not exist");
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if the artist already exists in the user's log
    //          and performs corresponding command
    public void addMusic() {
        System.out.println("Enter the artist that made the song:");
        String artistName = input.next();

        if (userML.isMusicianFound(artistName)) {
            System.out.println("Enter the song title of the " + artistName + " song:");
            String songName = input.next();
            addSongInfo(artistName, songName, userML.findMusician(artistName).isSongFound(songName));

        } else {
            addNewMusician(artistName);
        }
    }


    // MODIFIES: this
    // EFFECTS: adds the number of times played if the song already exists in the user's
    //          music library, otherwise creates a new entry for a song

    public void addSongInfo(String artistName, String songName, boolean isSongInList) {
        if (isSongInList) {
            System.out.println("Enter the amount of times you've played " + songName + " since your last log:");
            int timesPlayed = Integer.parseInt(input.next());
            userML.findMusician(artistName).findSong(songName).addTimesPlayed(timesPlayed);
            System.out.println("The number of times you played " + songName + " has updated!\n");
        } else {
            System.out.println("Enter the length of " + songName);
            double songLength = Double.parseDouble(input.next());
            System.out.println("Enter the amount of times you've played " + songName);
            int timesPlayed = Integer.parseInt(input.next());
            Song newSong = new Song(songName, songLength, timesPlayed);
            userML.findMusician(artistName).addSong(newSong);
            System.out.println("You have successfully added "
                    + songName + " to your " + artistName + " log!\n");
        }
    }

    // REQUIRES: At least one song should be added to the new musician entry
    // MODIFIES: this
    // EFFECTS: creates a new musician that the user has listened to,
    //          along with the songs the user has heard
    public void addNewMusician(String artistName) {
        Musician newArtist;
        List<Song> songsList = new ArrayList<>();
        String choice;
        boolean addMoreSongs = true;

        while (addMoreSongs) {
            displayAddSongMenu(artistName);

            choice = input.next();

            if (choice.equalsIgnoreCase("e")) {
                addMoreSongs = false;
            } else if (choice.equalsIgnoreCase("a")) {
                songsList.add(createSongInfo(artistName));
            } else {
                System.out.println("Input option does not exist");
            }
        }

        newArtist = new Musician(artistName, songsList);
        userML.addMusician(newArtist);
        System.out.println();
    }


    // EFFECTS: displays the options for adding a song
    public void displayAddSongMenu(String artistName) {
        System.out.println("Select from the options below:");
        System.out.println("\ta -> add a song to the '" + artistName + "' log");
        System.out.println("\te -> exit from adding songs");
    }

    // MODIFIES: this
    // EFFECTS: takes in song information from user and adds to new song entry
    public Song createSongInfo(String artistName) {
        Song newSong;

        System.out.println("Enter the song title of the " + artistName + " song:");
        String songName = input.next();

        System.out.println("Enter the time length of " + songName);
        double songLength = Double.parseDouble(input.next());

        System.out.println("Enter the amount of times you've played " + songName);
        int timesPlayed = Integer.parseInt(input.next());

        newSong = new Song(songName, songLength, timesPlayed);

        return newSong;
    }

    // EFFECTS: displays the most and least heard stats for the user,
    //          and then gives further viewing options
    public void getStats() {
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
    public void printArtistStat(Musician m) {
        Song mostHeard = m.getMostHeardSong();
        Song leastHeard = m.getLeastHeardSong();
        System.out.println("Artist: " + m.getName());
        System.out.println("Total time spent listening to: " + m.getTotalTimeListened());
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
    public void viewArtists() {
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
    public void viewMore() {
        String choice;
        boolean addMoreSongs = true;

        while (addMoreSongs) {
            viewArtistsMenu();
            choice = input.next();
            if (choice.equalsIgnoreCase("e")) {
                addMoreSongs = false;
            } else if (choice.equalsIgnoreCase("v")) {
                viewChoiceArtist();
            } else {
                System.out.println("Input option does not exist");
            }
        }
    }

    // EFFECTS: displays the options for viewing artists
    public void viewArtistsMenu() {
        System.out.println("Select from the options below:");
        System.out.println("\tv -> view the information from an artist");
        System.out.println("\te -> exit from stats");
    }

    // REQUIRES: the entered musician should appear in the user's music library
    // EFFECTS: displays the information about the requested artist
    public void viewChoiceArtist() {
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
