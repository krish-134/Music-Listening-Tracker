package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.List;

// This class contains the list of musicians that user has listened to
public class MusicLibrary implements MusicTracking, Writable {

    private List<Musician> musicians;

    // REQUIRES: name should be non-empty string
    // EFFECTS: creates list of musicians
    public MusicLibrary(List<Musician> musicians) {
        this.musicians = musicians;
    }

    // REQUIRES m should not already be in musiciansHeard
    // MODIFIES: this
    // EFFECTS: adds musician to list of listened musicians
    public void addMusician(Musician m) {
        musicians.add(m);
        EventLog.getInstance().logEvent(new Event("Added musician: " + m.getName()));
    }

    // EFFECTS: returns true if the given musician is in musiciansHeard
    public boolean isMusicianFound(String name) {
        for (Musician m: musicians) {
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
        for (Musician m: musicians) {
            if (name.equalsIgnoreCase(m.getName())) {
                return m;
            }
        }
        return null;
    }

    // REQUIRES: musiciansHeard should be non-empty
    // EFFECTS: returns the least played musician, assuming that no two musicians
    //          have been listened to by the exact same amount of time
    public Musician getMostHeardMusician() {
        Musician mostHeard = musicians.get(0);
        for (Musician m: musicians) {
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
        Musician leastHeard = musicians.get(0);
        for (Musician m: musicians) {
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
        for (Musician m: musicians) {
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

    public List<Musician> getMusicians() {
        return musicians;
    }


    // Credit: the following toJson methods is based off of
    //         the JsonSerializationDemo project from the
    //         CPSC 210 of the University of British Columbia

    // EFFECTS: returns music library as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("musicians", musiciansToJson());
        return json;
    }

    // EFFECTS: returns musicians from music library to a JSON array
    public JSONArray musiciansToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Musician m : musicians) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}
