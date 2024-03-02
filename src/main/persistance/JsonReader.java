package persistance;

import model.MusicLibrary;
import model.Musician;
import model.Song;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Credit: the following Class is heavily based off of
//         the JsonWriter class from the JsonSerializationDemo
//         project from the CPSC 210 course that is a part of the
//         University of British Columbia

public class JsonReader {

    private String source;

    // EFFECTS: creates reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads music library from file and returns it
    public MusicLibrary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMusicLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // Methods above this line are similar to the referenced
    // CPSC 210 project file in the credit note at the class signature

    // EFFECTS: parses music library from JSON object and returns it
    private MusicLibrary parseMusicLibrary(JSONObject jsonObject) {
        List<Musician> emptyMusicians = new ArrayList<>();
        MusicLibrary ml = new MusicLibrary(emptyMusicians);
        addMusicians(ml, jsonObject);
        return ml;
    }

    // MODIFIES: ml
    // EFFECTS: pareses musicians from JSON object and adds them tow music library
    private void addMusicians(MusicLibrary ml, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("musicians");
        for (Object json : jsonArray) {
            JSONObject nextMusician = (JSONObject) json;
            addMusician(ml, nextMusician);
        }
    }

    // MODIFIES: ml
    // EFFECTS: parses musician from JSON object and adds it to music library
    private void addMusician(MusicLibrary ml, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        List<Song> songs = new ArrayList<>();
        Musician musician = new Musician(name, songs);
        addSongs(ml, jsonObject, musician);
        ml.addMusician(musician);
    }

    // MODIFIES: ml & musician
    // EFFECTS: pareses song from JSON object and adds them tow music library
    private void addSongs(MusicLibrary ml, JSONObject jsonObject, Musician musician) {
        JSONArray jsonArray = jsonObject.getJSONArray("songs");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(ml, nextSong, musician);
        }
    }

    // MODIFIES: ml & musician
    // EFFECTS: parses musician from JSON object and adds it to music library
    private void addSong(MusicLibrary ml, JSONObject jsonObject, Musician musician) {
        String name = jsonObject.getString("name");
        double songLength = jsonObject.getDouble("songLength");
        int timesPlayed = jsonObject.getInt("timesPlayed");
        Song song = new Song(name, songLength, timesPlayed);
        musician.addSong(song);
    }




}
