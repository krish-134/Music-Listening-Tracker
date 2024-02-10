package model;

import model.Musician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MusicianTest {

    private Musician kendrick;
    private List<Song> kSongs;
    private Song mt;
    private Musician taylor;
    private List<Song> tSongs;
    private Song ah;
    private Song bs;
    private Song ls;

    @BeforeEach
    void setup() {
        mt = new Song("Money Trees", 6.27, 6, true);
        kSongs = new ArrayList<>();
        kSongs.add(mt);
        kendrick = new Musician("Kendrick Lamar", kSongs);

        ah = new Song("Anti-Hero", 3.23, 1, false);
        bs = new Song("Blank Space", 3.51, 12, true);
        ls = new Song("Love Story", 3.57, 9, false);
        tSongs = new ArrayList<Song>();
        tSongs.add(ah);
        tSongs.add(bs);
        tSongs.add(ls);
        taylor = new Musician("Taylor Swift", tSongs);

    //    bSongs = new ArrayList<>();
    //    bSongs.add("My Romance");
    //    bill = new Musician("Bill Evans", bSongs, 7.2);
    }

    @Test
    void testConstructor() {
        assertEquals("Taylor Swift", taylor.getName());
        assertEquals(tSongs, taylor.getSongsHeard());
        assertEquals(3, taylor.getSongsHeard().size());
        assertEquals(ah, taylor.getSongsHeard().get(0));
        assertEquals(bs, taylor.getSongsHeard().get(1));
        assertEquals(ls, taylor.getSongsHeard().get(2));
        assertEquals(78.48, taylor.getTotalTimeListened());
    }

    @Test
    void testAddSong() {

        assertEquals(3, taylor.getSongsHeard().size());

        Song red;
        red = new Song("Red", 3.43, 1, false);
        tSongs.add(red);

        assertEquals(4, taylor.getSongsHeard().size());
        assertEquals(red, taylor.getSongsHeard().get(3));
        assertEquals(3.43 + 78.48, taylor.getTotalTimeListened());

    }

    @Test
    void testIsSongFound() {}

    @Test
    void testGetSpecificSong() {
        assertEquals(ah, taylor.getSpecificSong("Anti-Hero"));
        assertEquals(bs, taylor.getSpecificSong("Blank Space"));
        assertEquals(ls, taylor.getSpecificSong("Love Story"));
    }

    @Test
    void testGetTimeListenedSong() {
        assertEquals(3.23, taylor.getTimeListenedSong("Anti-Hero"));
        assertEquals(3.51 * 12, taylor.getTimeListenedSong("Blank Space"));
        assertEquals(3.57 * 9, taylor.getTimeListenedSong("Love Story"));
    }

    @Test
    void testGetTotalTimeListened() {
        assertEquals(78.48, taylor.getTotalTimeListened());
    }

    @Test
    void testGetMostHeardSong() {
        assertEquals(bs, taylor.getMostHeardSong());
    }


    @Test
    void testGetLeastHeardSong() {
        assertEquals(ah, taylor.getLeastHeardSong());
    }

}
