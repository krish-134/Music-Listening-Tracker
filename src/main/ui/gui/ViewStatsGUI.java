package ui.gui;

import model.MusicLibrary;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ViewStatsGUI extends ButtonOperations {
    public  ViewStatsGUI(MusicLibrary userML, JTextArea textArea) {
        super(userML, textArea);
    }

    @Override
    // EFFECTS: saves the music library to file
    public void actionPerformed(ActionEvent e) {

    }
}
