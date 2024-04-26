package model;


import model.Event.Event;
import model.Event.EventLog;
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

    // REQUIRES m should not already be in musicians
    // MODIFIES: this
    // EFFECTS: adds musician to list of listened musicians
    public void addMusician(Musician m) {
        musicians.add(m);
        EventLog.getInstance().logEvent(new Event("Added musician: " + m.getName()));
    }

    // EFFECTS: returns true if the given musician is in musicians
    public boolean isMusicianFound(String name) {
        for (Musician m: musicians) {
            if (name.equalsIgnoreCase(m.getName())) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: m should already exist in musicians
    // EFFECTS: returns given musician if found in musicians, otherwise
    //          returns null if musician is not found
    public Musician findMusician(String name) {
        for (Musician m: musicians) {
            if (name.equalsIgnoreCase(m.getName())) {
                return m;
            }
        }
        return null;
    }

    // REQUIRES: musicians should be non-empty
    // EFFECTS: returns the least played musician, assuming that no two musicians
    //          have been listened to by the exact same amount of time
    public Musician getMostHeardMusician() {
        return getMostOrLeastHeard(true);
    }


    // REQUIRES: musicians should be non-empty
    // EFFECTS: returns the least played musician, assuming that no two musicians
    //          have been listened to by the exact same amount of time
    public Musician getLeastHeardMusician() {
        return getMostOrLeastHeard(false);
    }

    // REQUIRES: musicians should be non-empty
    // EFFECTS: returns either least listened to or most listened to musician based on mostHeard parameter
    public Musician getMostOrLeastHeard(boolean mostHeard) {
        Musician musicianHeard = musicians.get(0);
        for (Musician m: musicians) {
            double totalTime = m.getTotalTimeListened();
            double prevTotalTime = musicianHeard.getTotalTimeListened();
            if ((mostHeard && totalTime > prevTotalTime)
                    || (!mostHeard && totalTime < prevTotalTime)) {
                musicianHeard = m;
            }
        }
        return musicianHeard;
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
