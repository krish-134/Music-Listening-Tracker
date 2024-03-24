package ui.gui.graphs;

import model.Musician;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BarPlotDrawer {

    public static void drawBarPlot(Graphics g, Map<String, Integer> musicData) {
        int barWidth = 50;
        int spacing = 10;
        int x = 50;
        int maxHeight = 300;
        int baseY = 350;


        // Find the maximum time listened for scaling
        int maxTime = musicData.values().stream().max(Integer::compare).orElse(0);
        int scaleY = maxTime > maxHeight ? maxTime / maxHeight : 1;

        for (Map.Entry<String, Integer> entry : musicData.entrySet()) {
            String musicLabel = entry.getKey();
            int timeListened = entry.getValue();

            int heightScaled = timeListened / scaleY;

            g.setColor(Color.BLUE);
            g.fillRect(x, baseY - heightScaled, barWidth, heightScaled);

            g.setColor(Color.BLACK);
            g.drawString(musicLabel, x, baseY + 15);

            x += barWidth + spacing;
        }

        g.setColor(Color.BLACK);
        g.drawString("Time Listened", 10, 30);
        g.drawLine(30, 50, 30, baseY);
        g.drawLine(30, baseY, x + barWidth + spacing, baseY);

    }






}
