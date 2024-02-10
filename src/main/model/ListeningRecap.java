package model;

import java.util.List;

// This class contains the list of musicians that user has listened to
public class ListeningRecap implements MusicTracking {

    private String user;
    private List<Musician> musiciansHeard;

    // EFFECTS: creates list of musicians
    public ListeningRecap(String name, List<Musician> musiciansHeard) {
        user = name;
        this.musiciansHeard = musiciansHeard;
    }

    // MODIFIES: this
    // EFFECTS: adds musician to list of listened musicians
    public void addMusician(Musician m) {

    }

    // REQUIRES: musiciansHeard should be non-empty
    // EFFECTS: returns the least played musician, assuming that no two musicians
    //          have been listened to by the exact same amount of time
    public Musician getMostHeardMusician() {
        Musician mostHeard = musiciansHeard.get(0);
        for (Musician m: musiciansHeard) {
            if (m.getTotalTimeListened() > mostHeard.getTotalTimeListened()) {
                mostHeard = m;
            }
        }
        return mostHeard;
    }

    // REQUIRES: musiciansHeard should be non-empty
    // EFFECTS: returns the least played musician, assuming that no two musicians
    //          have been listened to by the exact same amount of time
    public Musician getLeastHeardMusician() {
        Musician leastHeard = musiciansHeard.get(0);
        for (Musician m: musiciansHeard) {
            if (m.getTotalTimeListened() < leastHeard.getTotalTimeListened()) {
                leastHeard = m;
            }
        }
        return leastHeard;
    }

    // EFFECTS: return total time spent listening to all musicians
    @Override
    public double getTotalTimeListened() {
        return 0;
    }

    public double getTimeListenedFromMusician(String name) {
        return 0;
    }

    // EFFECTS: returns musician with same name as parameter
    public Musician getMusician(String name) {
        return null;
    }

    @Override
    public String getName() {
        return user;
    }

}
