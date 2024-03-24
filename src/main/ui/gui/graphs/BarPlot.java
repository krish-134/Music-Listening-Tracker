package ui.gui.graphs;

import model.Musician;
import model.Song;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BarPlot extends JFrame {

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

    private void drawMusicianBarPlot(Graphics g, Musician musician) {
        int barWidth = 50;
        int spacing = 10;
        int x = 50;
        int maxHeight = 300;
        int baseY = 350;

        for (Song song : musician.getSongs()) {
            int heightScaled = (int) song.getTotalTimeListened();

            g.setColor(Color.MAGENTA);
            g.fillRect(x, baseY - heightScaled, barWidth, heightScaled);

            g.setColor(Color.BLACK);
            g.drawString(song.getName(), x, baseY + 15);

            x += barWidth + spacing;
        }

        g.setColor(Color.BLACK);
        g.drawString("Time Listened", 10, 30);
        g.drawString("Songs", x / 2, baseY + 40);

    }

}
