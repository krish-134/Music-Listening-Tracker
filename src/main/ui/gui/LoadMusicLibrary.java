package ui.gui;

import model.MusicLibrary;
import persistance.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoadMusicLibrary extends ButtonOperations {

    private static final String JSON_STORE = "./data/musicLibrary.json";
    private final JsonReader jsonReader;

    public  LoadMusicLibrary(MusicLibrary userML, JTextArea textArea) {
        super(userML, textArea);
        jsonReader = new JsonReader(JSON_STORE);
    }

    @Override
    // MODIFIES: userML
    // EFFECTS: loads the music library to file
    public void actionPerformed(ActionEvent e) {
        try {
            MusicLibrary musicLibraryFromFile = jsonReader.read();
            super.updateUserML(musicLibraryFromFile);
            //textArea.append("Loaded music library from " + JSON_STORE + "\n");
            textArea.setText("Loaded music library from " + JSON_STORE + "\n");
            super.updateTextAreaWithMusicians();
        } catch (IOException ie) {
            textArea.append("Unable to read from file: " + JSON_STORE + "\n");
        }
    }
}

