package ui.gui;

import model.MusicLibrary;
import model.Musician;
import ui.gui.plotting.BarPlot;
import ui.gui.plotting.BarPlotDrawer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// Class for dealing with viewing statistics from music library
public class ViewStatsGUI extends JFrame {
    private final MusicLibrary userML;

    // EFFECTS: sets the music library and initiates the GUI for viewing stats
    public  ViewStatsGUI(MusicLibrary userML) {
        this.userML = userML;
        initStatsGUI();
    }

    // EFFECTS: create GUI frame for statistics window
    private void initStatsGUI() {
        setTitle("Listening Statistics");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(makeChartPanel(), BorderLayout.CENTER);
        pack();

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    // EFFECTS: return the chart that shows music library stats
    private JPanel makeChartPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBarPlot(g);
            }
        };
        panel.setPreferredSize(new Dimension(600, 400));

        JButton searchBtn = new JButton("Search Musician Stats");
        searchBtn.addActionListener(e -> searchMusician());
        panel.add(searchBtn);
        return panel;
    }

    // EFFECTS: searches for a musician in the library
    private void searchMusician() {
        try {
            String musicianName = JOptionPane.showInputDialog("Enter Musician Name:");
            Musician musician = userML.findMusician(musicianName);

            if (musician != null) {
                showMusicianStats(musician);
            } else {
                JOptionPane.showMessageDialog(this, "Musician not found");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Input error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: shows a plot for a particular musician and their songs
    private void showMusicianStats(Musician musician) {
        BarPlot plotFrame = new BarPlot(musician.getName() + " Listening Statistics", musician);
        plotFrame.setVisible(true);
    }

    // EFFECTS: draws a bar plot with the musicians in the library
    private void drawBarPlot(Graphics g) {
        Map<String, Integer> musicianTimes = new HashMap<>();

        for (Musician m : userML.getMusicians()) {
            musicianTimes.put(m.getName(), (int) m.getTotalTimeListened());
        }

        BarPlotDrawer.drawBarPlot(g, musicianTimes);

    }

}
