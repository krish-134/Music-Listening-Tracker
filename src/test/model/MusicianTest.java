package model;

import model.Musician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MusicianTest {

    private Musician kendrick;

    private List<String> kSongs;
    private Musician taylor;
    private List<String> tSongs;
    private Musician bill;
    private List<String> bSongs;

    @BeforeEach
    void setup() {
        kSongs = new ArrayList<>();
        kSongs.add("Element");
        kSongs.add("Money Trees");
        kendrick = new Musician("Kendrick Lamar", kSongs, 20.5);

        tSongs = new ArrayList<>();
        tSongs.add("Anti-Hero");
        tSongs.add("Blank Space");
        tSongs.add("Love Story");
        taylor = new Musician("Taylor Swift", kSongs, 38.4);

        bSongs = new ArrayList<>();
        bSongs.add("My Romance");
        bill = new Musician("Bill Evans", bSongs, 7.2);
    }

    @Test
    void testConstructor() {
        assertEquals("Taylor Swift", taylor.getName());
        assertEquals(tSongs, taylor.getSongsHeard());
        assertEquals(3, taylor.getSongsHeard().size());
        assertEquals("Anti-Hero", taylor.getSongsHeard().get(0));
        assertEquals("Blank Space", taylor.getSongsHeard().get(1));
        assertEquals("Love Story", taylor.getSongsHeard().get(2));
        assertEquals(38.4, taylor.getTimeListened());

        assertEquals("Bill Evans", bill.getName());
        assertEquals(bSongs, bill.getSongsHeard());
        assertEquals(1, bill.getSongsHeard().size());
        assertEquals("My Romance", bill.getSongsHeard().get(0));
        assertEquals(7.2, bill.getTimeListened());

    }

    @Test
    void testAddTimeAndSameSong() {

        taylor.addTimeAndMaybeSong(3.9, "Blank Space");
        assertEquals(38.4 + 3.9, taylor.getTimeListened());
        assertEquals(3, taylor.getSongsHeard().size());
        assertEquals("Anti-Hero", taylor.getSongsHeard().get(0));
        assertEquals("Blank Space", taylor.getSongsHeard().get(1));
        assertEquals("Love Story", taylor.getSongsHeard().get(2));


        taylor.addTimeAndMaybeSong(3.7, "You Belong With Me");
        assertEquals(42.3 + 3.7, taylor.getTimeListened());
        assertEquals(4, taylor.getSongsHeard().size());
        assertEquals("You Belong With Me", taylor.getSongsHeard().get(3));

    }

}
