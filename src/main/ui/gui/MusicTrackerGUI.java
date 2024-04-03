package ui.gui;

import model.Event;
import model.EventLog;
import model.MusicLibrary;
import model.Musician;
import model.Song;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Creates the frame for the menu
public class MusicTrackerGUI extends JFrame {
    private static final String JSON_STORE = "./data/musicLibrary.json";
    private final JsonReader jsonReader;
    private final JsonWriter jsonWriter;
    private MusicLibrary userML;
    private JTextArea textArea;

    // EFFECTS: instantiates json reader and writer and the music library,
    //          then initiates the call to create GUI
    public MusicTrackerGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        List<Musician> artists = new ArrayList<>();
        userML = new MusicLibrary(artists);
        initGUI();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates the GUI for the main menu with four base options
    private void initGUI() {
        setTitle("Music Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        add(createTextArea(), BorderLayout.CENTER);
        add(createTopPanel(), BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }


    // EFFECTS: returns a text area
    private JScrollPane createTextArea() {
        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        return new JScrollPane(new JScrollPane(textArea));
    }

    // EFFECTS: creates the top panel of GUI where buttons are shown
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        topPanel.add(makeButton("add new music", e -> initAddMusicButton()));
        topPanel.add(makeButton("view statistics", e -> new ViewStatsGUI(userML)));
        topPanel.add(makeSaveLoadButtons());
        return topPanel;
    }

    // EFFECTS: creates a button for given action listener
    private JButton makeButton(String btnName, ActionListener listener) {
        JButton btn = new JButton(btnName);
        btn.addActionListener(listener);
        return btn;
    }

    // EFFECTS: deal with the user cases of adding new music to the library when
    //          the add music button is clicked in GUI
    private void initAddMusicButton() {
        try {
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
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Input error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a song to a musician already in the library
    private void addSongToExistingMusician(String artistName, String songName, double songLength, int timesPlayed) {
        Musician artist = userML.findMusician(artistName);
        if (artist.isSongFound(songName)) {
            artist.findSong(songName).addTimesPlayed(timesPlayed);
        } else {
            artist.addSong(new Song(songName, songLength, timesPlayed));
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new musician to library in order to add new song to library
    private void addNewMusicianToLibrary(String artistName, String songName, double songLength, int timesPlayed) {
        List<Song> songList = new ArrayList<>();
        songList.add(new Song(songName, songLength, timesPlayed));
        userML.addMusician(new Musician(artistName, songList));
    }

    // EFFECTS: creates the buttons for saving and loading the music library
    private JPanel makeSaveLoadButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(makeButton("Load Library", e -> loadMusicLibrary()), BorderLayout.WEST);
        panel.add(makeButton("Save Current Library", e -> saveMusicLibrary()), BorderLayout.EAST);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: loads the music library from file
    private void loadMusicLibrary() {
        try {
            userML = jsonReader.read();
            textArea.setText("Loaded music library from " + JSON_STORE + "\n");
            updateTextAreaWithMusicians();
        } catch (IOException ie) {
            textArea.append("Unable to read from file: " + JSON_STORE + "\n");
        }
    }

    // EFFECTS: saves current music library to file (overwrites previous file)
    private void saveMusicLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(userML);
            jsonWriter.close();
            textArea.append("Saved current Music Library to: " + JSON_STORE + "\n");
        } catch (FileNotFoundException fe) {
            textArea.append("Unable to write to file: " + JSON_STORE + "\n");
        }
    }

    // EFFECTS: displays the current musicians in library to screen
    private void updateTextAreaWithMusicians() {
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
