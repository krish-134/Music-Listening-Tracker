package model;

// This class tracks information about the user's behaviour
// with a given song
public class Song implements MusicTracking {

    private String name;
    private double songLength;
    private int timesPlayed;

    // REQUIRES: name is non-empty string, songLength > 0 and timesPlayed > 0
    // EFFECTS: creates a song with a name, the length, the amount of times the song has been played,
    //          and whether the song is a favourite
    public Song(String name, double songLength, int timesPlayed) {
        this.name = name;
        this.songLength = songLength;
        this.timesPlayed = timesPlayed;
    }

    // REQUIRES: t >= 0
    // MODIFIES: this
    // EFFECTS: adds amount of times the song has been played
    public void addTimesPlayed(int t) {
        timesPlayed += t;
    }

    // REQUIRES: songLength > 0 and timesPlayed > 0
    // MODIFIES: this
    // EFFECTS: returns the amount of time spent listening to the song
    @Override
    public double getTotalTimeListened() {
        return songLength * timesPlayed;
    }

    public String getName() {
        return name;
    }

    public double getSongLength() {
        return songLength;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }
}

