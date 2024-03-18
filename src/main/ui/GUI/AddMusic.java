package ui.GUI;

import model.MusicLibrary;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

public class AddMusic extends ButtonOperations {

    public  AddMusic(MusicLibrary userML, JTextArea textArea) {
        super(userML, textArea);
    }

    @Override
    // EFFECTS: saves the music library to file
    public void actionPerformed(ActionEvent e) {

    }
}
