package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.List;


// This class creates the information about a single musician/group
public class Musician implements MusicTracking, Writable {

    private String name;
    private List<Song> songs;

    // EFFECTS: Instantiates musician with a name, the list of their songs the user
    //          listened to, and the amount of time the user listened to the musician/group
    public Musician(String name, List<Song> songs) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Musician name cannot be empty");
        }
        this.name = name;
        this.songs = songs;
    }

    // MODIFIES: this
    // EFFECTS: adds a song to the songs listened to by user
    public void addSong(Song song) throws IllegalArgumentException {
        if (song == null) {
            throw new IllegalArgumentException();
        }
        if (!songs.contains(song)) {
            songs.add(song);
            EventLog.getInstance().logEvent(new Event("Added song, \"" + song.getName()
                    + "\", to musician: " + name));
        }
    }

    // EFFECTS: returns true if the given song is in songs
    public boolean isSongFound(String songTitle) {
        for (Song s: songs) {
            if (songTitle.equalsIgnoreCase(s.getName())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns given song if found in songs, otherwise
    //          returns null if song is not found
    public Song findSong(String songTitle) {
        for (Song s: songs) {
            if (songTitle.equals(s.getName())) {
                return s;
            }
        }
        return null;
    }

    // EFFECTS: returns time listened to given song, otherwise 0 if
    //          song title does not appear in songs
    public double getTimeListenedSong(String songTitle) {
        Song song = findSong(songTitle);
        if (findSong(songTitle) != null) {
            return song.getTotalTimeListened();
        }
        return 0;
    }

    // REQUIRES: songs should be non-empty
    // EFFECTS: returns a sum of the time spent listening to each song
    @Override
    public double getTotalTimeListened() {
        double sum = 0;
        for (Song s: songs) {
            sum += s.getTotalTimeListened();
        }
        return Math.round(sum * 100.0) / 100.0;
    }

    // REQUIRES: songs should be non-empty
    // EFFECTS: returns the least played song, assuming that no two songs
    //          have been listened to by the exact same amount of time
    public Song getMostHeardSong() {
        return getMostOrLeastHeard(true);
    }

    // REQUIRES: songsHeard should be non-empty
    // EFFECTS: returns the least played song, assuming that no two songs
    //          have been listened to by the exact same amount of time
    public Song getLeastHeardSong() {
        return getMostOrLeastHeard(false);
    }

    // REQUIRES: songs should be non-empty
    // EFFECTS: returns either least listened to or most listened to song based on mostHeard parameter
    public Song getMostOrLeastHeard(boolean mostHeard) {
        Song songHeard = songs.get(0);
        for (Song s: songs) {
            double totalTime = s.getTotalTimeListened();
            double prevTotalTime = songHeard.getTotalTimeListened();
            if ((mostHeard && totalTime > prevTotalTime)
                    || (!mostHeard && totalTime < prevTotalTime)) {
                songHeard = s;
            }
        }
        return songHeard;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public String getName() {
        return name;
    }

    // Credit: the following toJson methods is based off of
    //         the JsonSerializationDemo project from the
    //         CPSC 210 of the University of British Columbia

    // EFFECTS: returns musician as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("songs", songsToJson());
        return json;
    }

    // EFFECTS: returns songs from musician to a JSON array
    public JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : songs) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
