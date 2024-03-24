package ui.gui;

import model.MusicLibrary;
import model.Musician;
import model.Song;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MusicTrackerGUI extends JFrame {
    private static final String JSON_STORE = "./data/musicLibrary.json";
    private final JsonReader jsonReader;
    private final JsonWriter jsonWriter;
    private MusicLibrary userML;
    //private JTextField textField;
    private JTextArea textArea;

    public MusicTrackerGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        List<Musician> artists = new ArrayList<>();
        userML = new MusicLibrary(artists);
        initUI();

    }

    private void initUI() {
        setTitle("Music Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        //textField = new JTextField(20);
        //topPanel.add(textField);
        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        initButtons(topPanel);
        pack();

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initButtons(JPanel topPanel) {

        JButton addBtn = new JButton("add new music");
        addBtn.addActionListener(e -> initAddMusicButton(topPanel));
        topPanel.add(addBtn);

        JButton statsBtn = new JButton("view statistics");
        statsBtn.addActionListener(e -> new ViewStatsGUI(userML, textArea));
        topPanel.add(statsBtn);

        add(topPanel,BorderLayout.NORTH);

        initLoadAndSaveButtons(topPanel);

    }

    private void initAddMusicButton(JPanel topPanel) {

        String artistName = JOptionPane.showInputDialog("Enter musician name: ");
        String songName = JOptionPane.showInputDialog("Enter the song name: ");
        double songLength = Double.parseDouble(JOptionPane.showInputDialog("Enter the length of the song: "));
        int timesPlayed = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of times "
                + "you played the song: "));

        if (userML.isMusicianFound(artistName)) {
            addSongToExistingMusician(artistName, songName, songLength, timesPlayed);
        } else {
            addNewMusicianToLibrary(artistName, songName, songLength, timesPlayed);
        }

        updateTextAreaWithMusicians();
    }

    private void addSongToExistingMusician(String artistName, String songName, double songLength, int timesPlayed) {
        Musician artist = userML.findMusician(artistName);
        if (artist.isSongFound(songName)) {
            artist.findSong(songName).addTimesPlayed(timesPlayed);
        } else {
            artist.addSong(new Song(songName, songLength, timesPlayed));
        }
    }

    private void addNewMusicianToLibrary(String artistName, String songName, double songLength, int timesPlayed) {
        List<Song> songList = new ArrayList<>();
        songList.add(new Song(songName, songLength, timesPlayed));
        userML.addMusician(new Musician(artistName, songList));
    }

    private void initLoadAndSaveButtons(JPanel topPanel) {
        JButton loadBtn = new JButton("Load Library");
        loadBtn.addActionListener(e -> {
            try {
                userML = jsonReader.read();
                //textArea.append("Loaded music library from " + JSON_STORE + "\n");
                textArea.setText("Loaded music library from " + JSON_STORE + "\n");
                updateTextAreaWithMusicians();
            } catch (IOException ie) {
                textArea.append("Unable to read from file: " + JSON_STORE + "\n");
            }
        });

        topPanel.add(loadBtn, BorderLayout.WEST);

        JButton saveBtn = new JButton("Save Current Library");
        saveBtn.addActionListener(e -> {
            try {
                jsonWriter.open();
                jsonWriter.write(userML);
                jsonWriter.close();
                textArea.append("Saved current Music Library to: " + JSON_STORE + "\n");
            } catch (FileNotFoundException fe) {
                textArea.append("Unable to write to file: " + JSON_STORE + "\n");
            }
        });

        topPanel.add(saveBtn, BorderLayout.EAST);
    }

    // EFFECTS: displays the current musicians in library to screen
    protected void updateTextAreaWithMusicians() {
        //textArea.setText("");
        Set<String> displayedMusicians = new HashSet<>();
        String[] textLines = textArea.getText().split("\n");

        for (String artist : textLines) {
            displayedMusicians.add(artist.trim());
        }

        for (Musician m : userML.getMusicians()) {
            String artistName = m.getName();
            if (!displayedMusicians.contains(artistName)) {

                textArea.append(artistName + "\n");
            }
        }
    }

}
