package ui.GUI;

import model.MusicLibrary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

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


}
