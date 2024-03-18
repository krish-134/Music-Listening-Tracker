package ui;

import model.MusicLibrary;
import model.Musician;

import javax.swing.*;
import java.awt.*;

public class DisplayMusicLibrary extends JFrame {

    private static final int WIDTH = 750;
    private static final int HEIGHT = 600;

    private MusicTrackerApp mta;
    private MusicLibrary userML;
    private JPanel menuPanel;
    private JPanel listPanel;
    JLabel label;

    public DisplayMusicLibrary(MusicTrackerApp mta, MusicLibrary musicLibrary) {
        super("Music Library Tracker");
        this.mta = mta;
        this.userML = musicLibrary;
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

        createMenuPanel();
        createListPanel();
    }

    private void createMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(WIDTH, 90));
        menuPanel.setLayout(new FlowLayout());
        menuPanel.setBackground(new Color(105, 105, 105));
        add(menuPanel, BorderLayout.NORTH);
        setButtonsMainMenu();

    }

    private void createListPanel() {
        listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.setBackground(new Color(105, 90, 105));

        add(listPanel, BorderLayout.CENTER);

    }

    private void setButtonsMainMenu() {
        JButton button1 = new JButton("add new music");

        JButton button2 = new JButton("statistics");

        JButton button3 = new JButton("load music library");

        JButton button4 = new JButton("save music library");

        menuPanel.add(button1);
        menuPanel.add(button2);
        menuPanel.add(button3);
        menuPanel.add(button4);

        button1.addActionListener(e -> displayMusicList());
        button2.addActionListener(e -> {
            this.remove(menuPanel);
            revalidate();
            mainViewStatsAction();
        });

        button3.addActionListener(e -> {
            mta.nextCommand("3");
            displayMusicList();
        });

        button4.addActionListener(e -> mta.nextCommand("4"));
    }

    private void mainViewStatsAction() {
        JButton btnViwArtist = new JButton("view specific artist");

        JButton btnToMain = new JButton("exit to main menu");

        add(btnViwArtist);
        add(btnToMain);

        mta.nextCommand("2");

    }

    private void displayMusicList() {
        listPanel.removeAll();

        DefaultListModel<String> musicianNames = new DefaultListModel<>();
        DefaultListModel<String> songNames = new DefaultListModel<>();


        for (Musician m : mta.getUserML().getMusicians()) {
            musicianNames.addElement(m.getName());
            //for (Song s : userML.getMusicians()) {
            //    songNames.addElement(s.getName());
            //}
        }

        JList<String> musicians = new JList<>(musicianNames);
        musicians.setBounds(200, 200, 12, 12);
        listPanel.add(new JScrollPane(musicians), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

}
