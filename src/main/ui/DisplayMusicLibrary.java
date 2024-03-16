package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DisplayMusicLibrary extends JFrame {

    private static final int WIDTH = 900;
    private static final int HEIGHT = 700;

    private MusicTrackerApp mta;

    private JPanel panel;
    JLabel label;

    public DisplayMusicLibrary(MusicTrackerApp mta) {
        super("Music Library Tracker");
        this.mta = mta;
        initializeDisplay();
    }


    // MODIFIES: this
    // EFFECTS: creates initial settings for display
    private void initializeDisplay() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(105, 105, 105));
        setVisible(true);

        setButtonsMainMenu();
    }

    private void firstMenu() {

        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0,1));
        panel.setBounds(0, 0, WIDTH, HEIGHT);
        panel.setBackground(new Color(105, 105, 105));
        setButtonsMainMenu();

        add(panel, BorderLayout.CENTER);

    }

    private void setButtonsMainMenu() {
        JButton button1 = new JButton("add new music");
        button1.setBounds(50, 80, 150, 75);

        JButton button2 = new JButton("statistics");
        button2.setBounds(210, 80, 150, 75);

        JButton button3 = new JButton("load music library");
        button3.setBounds(370, 80, 150, 75);

        JButton button4 = new JButton("save music library");
        button4.setBounds(530, 80, 150, 75);

        JButton button5 = new JButton("exit program");
        button5.setBounds(690, 80, 150, 75);

        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);

        button1.addActionListener(e -> mta.nextCommand("1"));
        button2.addActionListener(e -> mta.nextCommand("2"));
        button3.addActionListener(e -> mta.nextCommand("3"));
        button4.addActionListener(e -> mta.nextCommand("4"));
        button5.addActionListener(e -> mta.nextCommand("5"));

    }

    private void mainMenuAction() {

    }

}
