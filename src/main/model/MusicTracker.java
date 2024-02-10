package model;

public abstract class MusicTracker {

    // EFFECTS: returns the amount of time spent listening to given object
    protected abstract double getTotalTimeListened();// EFFECTS: returns the most played song
/*
    protected abstract Song getMostPlayed() {
        Song mostPlayed = songsHeard.get(0);
        for (Song s: songsHeard) {
            if (s.getTotalTimeListened() > mostPlayed.getTotalTimeListened()) {
                mostPlayed = s;
            }
        }
        return mostPlayed;
    }


    // EFFECTS: returns the least played song
    protected abstract Song getLeastPlayed() {
        Song leastPlayed = songsHeard.get(0);
        for (Song s: songsHeard) {
            if (s.getTotalTimeListened() < leastPlayed.getTotalTimeListened()) {
                leastPlayed = s;
            }
        }
        return leastPlayed;
    }
*/

    // EFFECTS: returns name of given object
    protected abstract String getName();

}
