package model;

import java.util.List;

// This class contains the list of musicians that user has listened to
public class MusicLibrary implements MusicTracking {

    private String user;
    private List<Musician> musiciansHeard;

    // REQUIRES: name should be non-empty string
    // EFFECTS: creates list of musicians
    public MusicLibrary(String name, List<Musician> musiciansHeard) {
        user = name;
        this.musiciansHeard = musiciansHeard;
    }

    // REQUIRES m should not already be in musiciansHeard
    // MODIFIES: this
    // EFFECTS: adds musician to list of listened musicians
    public void addMusician(Musician m) {
        musiciansHeard.add(m);
    }

    // EFFECTS: returns true if the given musician is in musiciansHeard
    public boolean isMusicianFound(String name) {
        for (Musician m: musiciansHeard) {
            if (name.equalsIgnoreCase(m.getName())) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: m should already exist in musiciansHeard
    // EFFECTS: returns given musician if found in musiciansHeard, otherwise
    //          returns null if musician is not found
    public Musician findMusician(String name) {
        for (Musician m: musiciansHeard) {
            if (name.equals(m.getName())) {
                return m;
            }
        }
        return null;
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
        double sum = 0;
        for (Musician m: musiciansHeard) {
            sum += m.getTotalTimeListened();
        }
        return Math.round(sum * 100.0) / 100.0;
    }


    // EFFECTS: returns total time listened to given musician, returns 0 if the musician
    //          is not found
    public double getTimeListenedMusician(String name) {
        Musician m = findMusician(name);
        if (findMusician(name) != null) {
            return m.getTotalTimeListened();
        }
        return 0;
    }


    @Override
    public String getName() {
        return user;
    }

    public List<Musician> getMusiciansHeard() {
        return musiciansHeard;
    }

}
