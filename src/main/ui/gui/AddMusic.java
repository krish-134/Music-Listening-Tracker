package ui.gui;

import model.MusicLibrary;
import model.Musician;
import model.Song;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddMusic extends ButtonOperations {


    private MusicLibrary userML;

    private JTextArea textArea;

    public  AddMusic(MusicLibrary userML, JTextArea textArea) {
        //super(userML, textArea);
        super(userML, textArea);
        this.userML = userML;
        this.textArea = textArea;
    }

    @Override
    // EFFECTS: adds the music to the music library
    //          and displays list of current musicians
    public void actionPerformed(ActionEvent e) {
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
