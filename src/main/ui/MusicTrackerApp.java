package ui;

import model.ListeningHistory;

import model.Musician;

import model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This UI class performs all the print statements and considers branches for user's command
// The structuring of this UI is based off of the TellerApp program from CPSC 210 practice problems
public class MusicTrackerApp {

    private Scanner input;
    private ListeningHistory userLH;


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

        userLH = new ListeningHistory("Johny", artists);

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


    public void nextCommand(String choice) {
        if (choice.equalsIgnoreCase("a")) {
            addMusic();
        } else if (choice.equalsIgnoreCase("s")) {
            getStats();
        } else {
            System.out.println("Input option does not exist");
        }
    }

    public void addMusic() {
        System.out.println("Enter the artist that made the song:");
        String artistName = input.next();

        if (userLH.isMusicianFound(artistName)) {
            System.out.println("Enter the song title of the " + artistName + " song:");
            String songName = input.next();

            addSongInfo(artistName, songName, userLH.findMusician(artistName).isSongFound(songName));

        } else {
            addNewMusician(artistName);
        }

    }

    public void addSongInfo(String artistName, String songName, boolean isSongInList) {
        if (isSongInList) {
            System.out.println("Enter the amount of times you've played " + songName + " since your last log:");
            int timesPlayed = Integer.parseInt(input.next());
            userLH.findMusician(artistName).findSong(songName).addTimesPlayed(timesPlayed);
            System.out.println("The number of times you played " + songName + " has updated!\n");
        } else {
            System.out.println("Enter the length of " + songName);
            double songLength = Double.parseDouble(input.next());
            System.out.println("Enter the amount of times you've played " + songName);
            int timesPlayed = Integer.parseInt(input.next());
            Song newSong = new Song(songName, songLength, timesPlayed);
            userLH.findMusician(artistName).addSong(newSong);
            System.out.println("You have successfully added "
                    + songName + " to your " + artistName + " log!\n");
        }
    }

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

        userLH.addMusician(newArtist);
        System.out.println();

    }

    public void displayAddSongMenu(String artistName) {
        System.out.println("Select from the options below:");
        System.out.println("\ta -> add a song to the " + artistName + " log");
        System.out.println("\te -> exit from adding songs");
    }

    public Song createSongInfo(String artistName) {
        Song newSong;

        System.out.println("Enter the song title of the " + artistName + " song:");
        String songName = input.next();

        System.out.println("Enter the length of " + songName);
        double songLength = Double.parseDouble(input.next());

        System.out.println("Enter the amount of times you've played " + songName);
        int timesPlayed = Integer.parseInt(input.next());

        newSong = new Song(songName, songLength, timesPlayed);

        return newSong;
    }

    public void getStats() {
        Musician mostHeard = userLH.getMostHeardMusician();
        Musician leastHeard = userLH.getMostHeardMusician();

        System.out.println(userLH.getName() + "'s total listening time: "
                + userLH.getTotalTimeListened() + " minutes\n");

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

    public void printArtistStat(Musician m) {
        System.out.println("Artist: " + m.getName());
        System.out.println("Total time spent listening to: " + m.getTotalTimeListened());
        System.out.println("Most listened to song by: '" + m.getMostHeardSong().getName()
                + "' for a total of " + m.getMostHeardSong().getTotalTimeListened() + " minutes");
        System.out.println("Least listened to song by: '" + m.getLeastHeardSong().getName()
                + "' for a total of " + m.getLeastHeardSong().getTotalTimeListened() + " minutes");

        System.out.println("ALl the " + m.getName() + " songs you have listened to include:\n");
        for (Song s: m.getSongsHeard()) {
            System.out.println("Song: " + s.getName());
            System.out.println("Song-length: " + s.getSongLength() + " minutes");
            System.out.println("Time listened: " + s.getTotalTimeListened() + " minutes\n");
        }

    }

    public void viewArtists() {
        int numArtists = userLH.getMusiciansHeard().size();
        System.out.println("You have listened to " + numArtists + " musician(s)!");
        System.out.println("A list of all of your musicians include:\n");

        for (Musician m: userLH.getMusiciansHeard()) {
            System.out.println(m.getName());
        }

        viewMore();
    }

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

    public void viewArtistsMenu() {
        System.out.println("Select from the options below:");
        System.out.println("\tv -> view the information from an artist");
        System.out.println("\te -> exit from stats");
    }

    public void viewChoiceArtist() {
        String artistName;
        System.out.println("Enter the artist whose information you wish to view:");
        artistName = input.next();

        if (userLH.isMusicianFound(artistName)) {
            printArtistStat(userLH.findMusician(artistName));
        } else {
            System.out.println("This artist name is not in your music log\n");
        }

    }

}
