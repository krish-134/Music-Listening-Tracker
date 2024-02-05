package model;


// This class information and operations about individual musician/group

import java.util.ArrayList;
import java.util.List;

public class Musician {
    // delete or rename this class!

    private String name;
    private List<String> songsHeard;
    private double timeListened;


    // REQUIRES: The songsHeard list should be non-empty and timeListened > 0
    // EFFECTS: Instantiates musician with a name, the list of their songs the user
    //          listened to, and the amount of time they listened to the musician/group
    public Musician(String name, List<String> songsHeard, double timeListened) {
        this.name = name;
        //this.songsHeard = new ArrayList<String>();
        this.songsHeard = songsHeard;
        this.timeListened = timeListened;

    }

    // REQUIRES: songsHeard is non-empty and timeListened > 0
    // MODIFIES: this
    // EFFECTS: adds time to listening time and if a new song is heard,
    //          add song to songsHeard
    public void addTimeAndMaybeSong(double time, String song) {

    }

    // REQUIRES: songsHeard should be non-empty
    // MODIFIES: this
    // EFFECTS: adds a song to the songs listened to by user
    public void addSong(String song) {

    }

    // REQUIRES: timeListened > 0
    // MODIFIES: this
    // EFFECTS: updates time in minutes that user has listened to musician
    public void addTimeListened(double time) {

    }

    // EFFECTS: returns true if the given song is in the songs heard list
    public boolean getSpecificSongHeard(String song) {

        return false;
    }

    // getters

    public List<String> getSongsHeard() {
        return songsHeard;
    }


    public double getTimeListened() {
        return timeListened;
    }

    public String getName() {
        return name;
    }




}
