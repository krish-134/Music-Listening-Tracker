package model;


// This class creates the information about a single musician/group
import java.util.List;

public class Musician implements MusicTracking {

    private String name;
    private List<Song> songsHeard;

    // REQUIRES: The songsHeard list should be non-empty and name should be non-empty string
    // EFFECTS: Instantiates musician with a name, the list of their songs the user
    //          listened to, and the amount of time the user listened to the musician/group
    public Musician(String name, List<Song> songsHeard) {
        this.name = name;
        this.songsHeard = songsHeard;
    }

    // REQUIRES: songsHeard should be non-empty and song should
    //           not already exist in songsHeard
    // MODIFIES: this
    // EFFECTS: adds a song to the songs listened to by user
    public void addSong(Song song) {
        songsHeard.add(song);
    } //THROW EXCEPTION IF SONG IS ALREADY IN LIST!??!?!

    // EFFECTS: returns true if the given song is in songsHeard
    public boolean isSongFound(String songTitle) {
        for (Song s: songsHeard) {
            if (songTitle.equalsIgnoreCase(s.getName())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns given song if found in songsHeard, otherwise
    //          returns null if song is not found
    public Song findSong(String songTitle) {
        for (Song s: songsHeard) {
            if (songTitle.equals(s.getName())) {
                return s;
            }
        }
        return null;
    }

    // EFFECTS: returns time listened to given song, otherwise 0 if
    //          song title does not appear in songsHeard
    public double getTimeListenedSong(String songTitle) {
        Song song = findSong(songTitle);
        if (findSong(songTitle) != null) {
            return song.getTotalTimeListened();
        }
        return 0;
    }

    // REQUIRES: songsHeard should be non-empty
    // EFFECTS: returns a sum of the time spent listening to each song
    @Override
    public double getTotalTimeListened() {
        double sum = 0;
        for (Song s: songsHeard) {
            sum += s.getTotalTimeListened();
        }
        return Math.round(sum * 100.0) / 100.0;
    }

    // REQUIRES: songsHeard should be non-empty
    // EFFECTS: returns the least played song, assuming that no two songs
    //          have been listened to by the exact same amount of time
    public Song getMostHeardSong() {
        Song mostHeard = songsHeard.get(0);
        for (Song s: songsHeard) {
            if (s.getTotalTimeListened() > mostHeard.getTotalTimeListened()) {
                mostHeard = s;
            }
        }
        return mostHeard;
    }

    // REQUIRES: songsHeard should be non-empty
    // EFFECTS: returns the least played song, assuming that no two songs
    //          have been listened to by the exact same amount of time
    public Song getLeastHeardSong() {
        Song leastHeard = songsHeard.get(0);
        for (Song s: songsHeard) {
            if (s.getTotalTimeListened() < leastHeard.getTotalTimeListened()) {
                leastHeard = s;
            }
        }
        return leastHeard;
    }

    public List<Song> getSongsHeard() {
        return songsHeard;
    }

    @Override
    public String getName() {
        return name;
    }


}
