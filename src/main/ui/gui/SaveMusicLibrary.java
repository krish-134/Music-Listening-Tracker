package ui.gui;

import model.MusicLibrary;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

public class SaveMusicLibrary extends ButtonOperations {

    private static final String JSON_STORE = "./data/musicLibrary.json";

    private final JsonWriter jsonWriter;

    public  SaveMusicLibrary(MusicLibrary userML, JTextArea textArea) {
        super(userML, textArea);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    @Override
    // EFFECTS: saves the music library to file
    public void actionPerformed(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.write(userML);
            jsonWriter.close();
            textArea.append("Saved current Music Library to: " + JSON_STORE + "\n");
        } catch (FileNotFoundException fe) {
            textArea.append("Unable to write to file: " + JSON_STORE + "\n");
        }
    }


}
