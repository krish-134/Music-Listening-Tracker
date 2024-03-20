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



    protected void updateUserML(MusicLibrary musicLibrary) {
        userML = musicLibrary;
    }

}
