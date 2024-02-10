package model;

// This class tracks information about the user's behaviour
// with a given song
public class Song implements MusicTracking {

    private String name;
    private double songLength;
    private int timesPlayed;

    private boolean isFav;

    // REQUIRES: songLength > 0 and timesPlayed > 0
    // EFFECTS: creates a song with a name, the length, the amount of times the song has been played,
    //          and whether the song is a favourite
    public Song(String name, double songLength, int timesPlayed, Boolean isFav) {
        this.name = name;
        this.songLength = songLength;
        this.timesPlayed = timesPlayed;
        this.isFav = isFav;
    }

    // MODIFIES: this
    // EFFECTS: adds amount of times the song has been played
    public void addTimesPlayed(int t) {
        timesPlayed += t;
    }

    // MODIFIES: this
    // EFFECTS: changes the favourite status of the song
    public void changeFav() {
        isFav = !isFav;
    }

    // REQUIRES: songLength > 0 and timesPlayed > 0
    // MODIFIES: this
    // EFFECTS: returns the amount of time spent listening to the song
    @Override
    public double getTotalTimeListened() {
        return songLength * timesPlayed;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean getIsFav() {
        return isFav;
    }



}

