package ui.GUI;

import model.MusicLibrary;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadMusicLibrary extends ButtonOperations {

    private String jsonStore;
    private JsonReader jsonReader;

    public  LoadMusicLibrary(MusicLibrary userML, JTextArea textArea, String jsonStore) {
        super(userML, textArea);
        this.jsonStore = jsonStore;
        jsonReader = new JsonReader(jsonStore);
    }

    @Override
    // MODIFIES: userML
    // EFFECTS: loads the music library to file
    public void actionPerformed(ActionEvent e) {
        try {
            userML = jsonReader.read();
            textArea.append("Loaded music library from " + jsonStore + "\n");
        } catch (IOException ie) {
            textArea.append("Unable to read from file: " + jsonStore + "\n");
        }
    }
}

