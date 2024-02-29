package persistance;

import model.MusicLibrary;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Credit: the following Class is heavily based off of
//         the JsonWriter class from the JsonSerializationDemo
//         project from the CPSC 210 course that is a part of the
//         University of British Columbia

// Represents a writer that writes JSON representation of MusicLibrary to file
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: creates the writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer and throws FileNotFoundException if destination cannot
    //          be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Music to file
    public void write(MusicLibrary ml) {
        JSONObject json = ml.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    private void saveToFile(String json) {
        writer.print(json);
    }

}
