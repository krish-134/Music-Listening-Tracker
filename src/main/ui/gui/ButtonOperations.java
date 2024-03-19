package ui.gui;

import model.MusicLibrary;
import model.Musician;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public abstract class ButtonOperations implements ActionListener {

    protected MusicLibrary userML;

    protected JTextArea textArea;

    public ButtonOperations(MusicLibrary userML, JTextArea textArea) {
        this.userML = userML;
        this.textArea = textArea;
    }

    @Override
    // EFFECTS: saves the music library to file
    public abstract void actionPerformed(ActionEvent e);

    // EFFECTS: displays the current musicians in library to screen
    protected void updateTextAreaWithMusicians() {
        //textArea.setText("");
        Set<String> displayedMusicians = new HashSet<>();
        String[] textLines = textArea.getText().split("\n");

        for (String artist : textLines) {
            displayedMusicians.add(artist.trim());
        }

        for (Musician m : userML.getMusicians()) {
            String artistName = m.getName();
            if (!displayedMusicians.contains(artistName)) {

                textArea.append(artistName + "\n");
            }
        }
    }

    protected void updateUserML(MusicLibrary musicLibrary) {
        userML = musicLibrary;
    }

}
