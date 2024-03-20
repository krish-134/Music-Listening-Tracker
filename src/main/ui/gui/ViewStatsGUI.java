package ui.gui;

import model.MusicLibrary;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ViewStatsGUI extends ButtonOperations {
    private JPanel graphPanel;

    public  ViewStatsGUI(MusicLibrary userML, JTextArea textArea) {
        super(userML, textArea);
        this.graphPanel = graphPanel;
    }

    @Override
    // EFFECTS: saves the music library to file
    public void actionPerformed(ActionEvent e) {

    }

   // private void displayBarGraph() {
    // DefaultCategoryDataset dataset = new DefaultCategoryDataset();
   // }

}
