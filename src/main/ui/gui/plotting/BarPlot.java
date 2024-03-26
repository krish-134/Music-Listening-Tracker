package ui.gui.plotting;

import model.Musician;
import model.Song;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// Creates frame for bar plot
public class BarPlot extends JFrame {

    // EFFECTS: creates a panel for bar plot that has a title and related musician
    public BarPlot(String title, Musician musician) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel musicianPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawMusicianBarPlot(g, musician);
            }
        };

        musicianPanel.setPreferredSize(new Dimension(600, 400));
        add(musicianPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);

    }

    // MODIFIES: g
    // EFFECTS: Creates plot for particular musician
    private void drawMusicianBarPlot(Graphics g, Musician musician) {
        Map<String, Integer> musicData = new HashMap<>();

        for (Song song : musician.getSongs()) {
            musicData.put(song.getName(), (int) song.getTotalTimeListened());
        }

        BarPlotDrawer.drawBarPlot(g, musicData);

    }

}
