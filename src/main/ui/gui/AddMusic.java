package ui.gui;

import model.MusicLibrary;
import model.Musician;
import model.Song;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class AddMusic extends ButtonOperations {

    public  AddMusic(MusicLibrary userML, JTextArea textArea) {
        super(userML, textArea);
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

        super.updateTextAreaWithMusicians();
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


}
