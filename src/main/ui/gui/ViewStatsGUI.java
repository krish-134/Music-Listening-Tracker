package ui.gui;

import model.MusicLibrary;
import model.Musician;
import model.Song;
import ui.gui.graphs.BarPlot;
import ui.gui.graphs.BarPlotDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class ViewStatsGUI extends JFrame {
    private final MusicLibrary userML;
    private final JTextArea textArea;

    public  ViewStatsGUI(MusicLibrary userML, JTextArea textArea) {
        this.userML = userML;
        this.textArea = textArea;
        initStatsGUI();
    }

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

    private void searchMusician() {
        String musicianName = JOptionPane.showInputDialog("Enter Musician Name:");
        Musician musician = userML.findMusician(musicianName);

        if (musician != null) {
            showMusicianStats(musician);
        } else {
            JOptionPane.showMessageDialog(this, "Musician not found");
        }
    }

    private void showMusicianStats(Musician musician) {
        BarPlot plotFrame = new BarPlot(musician.getName() + " Listening Statistics", musician);
        plotFrame.setVisible(true);
    }

    private void drawBarPlot(Graphics g) {
        Map<String, Integer> musicianTimes = new HashMap<>();

        for (Musician m : userML.getMusicians()) {
            musicianTimes.put(m.getName(), (int) m.getTotalTimeListened());
        }

        BarPlotDrawer.drawBarPlot(g, musicianTimes);

    }

}
