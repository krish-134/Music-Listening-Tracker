package ui.gui.graphs;

import model.MusicLibrary;
import model.Musician;

import javax.swing.*;
import java.awt.*;

public class MusicianBarPlot extends JPanel {
    private MusicLibrary userML;

    public MusicianBarPlot(MusicLibrary userML) {
        this.userML = userML;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 50;
        int barWidth = 30;
        int maxHeight = getHeight() - 100;
        int maxVal = (int) userML.getTotalTimeListened();

        drawPlot();
    }

    public void drawPlot() {
        for (Musician m : userML.getMusicians()) {

        }
    }

}
