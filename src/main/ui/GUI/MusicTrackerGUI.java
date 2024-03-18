package ui.GUI;

import model.MusicLibrary;
import model.Musician;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicTrackerGUI extends JFrame {
    private static final String JSON_STORE = "./data/musicLibrary.json";

    //private Scanner input;
    private MusicLibrary userML;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JTextField textField;
    private JTextArea textArea;

    public MusicTrackerGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initUI();

        List<Musician> artists = new ArrayList<>();
        userML = new MusicLibrary(artists);
    }

    private void initUI() {
        setTitle("Music Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        textField = new JTextField(20);
        topPanel.add(textField);

        initButtons(topPanel);

        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initButtons(JPanel topPanel) {

        JButton addBtn = new JButton("add new music");
        addBtn.addActionListener(new AddMusic(userML, textArea));
        topPanel.add(addBtn);

        JButton statsBtn = new JButton("view statistics");
        statsBtn.addActionListener(new ViewStatsGUI(userML, textArea));
        topPanel.add(statsBtn);

        add(topPanel,BorderLayout.NORTH);

        JButton loadBtn = new JButton("Load Library");
        loadBtn.addActionListener(new LoadMusicLibrary(userML, textArea, JSON_STORE));
        topPanel.add(loadBtn, BorderLayout.WEST);

        JButton saveBtn = new JButton("Save Current Library");
        saveBtn.addActionListener(new SaveMusicLibrary(userML, textArea, JSON_STORE));
        topPanel.add(saveBtn, BorderLayout.EAST);

    }



}
