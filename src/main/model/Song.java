package model;

import org.json.JSONObject;
import persistance.Writable;

// This class tracks information about the user's behaviour
// with a given song
public class Song implements MusicTracking, Writable {

    private final String name;
    private final double songLength;
    private int timesPlayed;

    // REQUIRES: name is non-empty string, songLength > 0 and timesPlayed > 0
    // EFFECTS: creates a song with a name, the length, the amount of times the song has been played,
    //          and whether the song is a favourite
    public Song(String name, double songLength, int timesPlayed) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Song name cannot be empty");
        }
        if (songLength <= 0) {
            throw new IllegalArgumentException("Song length cannot be less than or equal to zero");
        }
        if (timesPlayed <= 0) {
            throw new IllegalArgumentException("Times played cannot be less than or equal to zero");
        }
        this.name = name;
        this.songLength = songLength;
        this.timesPlayed = timesPlayed;
    }

    // MODIFIES: this
    // EFFECTS: adds amount of times the song has been played
    public void addTimesPlayed(int t) throws IllegalArgumentException {
        if (t < 0) {
            throw new IllegalArgumentException("Cannot add " + t + " on to times played");
        }
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

    // Credit: the following toJson method is based off of
    //         the JsonSerializationDemo project from the
    //         CPSC 210 of the University of British Columbia

    // EFFECTS: returns song as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("songLength", songLength);
        json.put("timesPlayed", timesPlayed);
        return json;
    }

}

