package ui.GUI;

import model.MusicLibrary;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveMusicLibrary extends ButtonOperations {

    private String jsonStore;

    private JsonWriter jsonWriter;

    public  SaveMusicLibrary(MusicLibrary userML, JTextArea textArea,  String jsonStore) {
        super(userML, textArea);
        this.jsonStore = jsonStore;
        jsonWriter = new JsonWriter(jsonStore);
    }

    @Override
    // EFFECTS: saves the music library to file
    public void actionPerformed(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.write(userML);
            jsonWriter.close();
            textArea.append("Saved current Music Library to: " + jsonStore + "\n");
        } catch (FileNotFoundException fe) {
            textArea.append("Unable to write to file: " + jsonStore + "\n");
        }
    }
}
