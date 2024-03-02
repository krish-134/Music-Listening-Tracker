package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MusicLibraryTest {

    private MusicLibrary userML;
    private List<Musician> musicians;
    private Musician kendrick;
    private List<Song> kenSongs;
    private Song mt;
    private Musician taylor;
    private List<Song> taySongs;
    private Song ah;
    private Song bs;
    private Song ls;

    @BeforeEach
    void setup() {
        mt = new Song("Money Trees", 6.27, 6);
        kenSongs = new ArrayList<>();
        kenSongs.add(mt);
        kendrick = new Musician("Kendrick Lamar", kenSongs);

        ah = new Song("Anti-Hero", 3.23, 1);
        bs = new Song("Blank Space", 3.51, 12);
        ls = new Song("Love Story", 3.57, 9);
        taySongs = new ArrayList<>();
        taySongs.add(ah);
        taySongs.add(bs);
        taySongs.add(ls);
        taylor = new Musician("Taylor Swift", taySongs);

        musicians = new ArrayList<>();
        musicians.add(kendrick);
        musicians.add(taylor);
        userML = new MusicLibrary(musicians);

    }

    @Test
    void testConstructor() {
        assertEquals(musicians, userML.getMusicians());
    }

    @Test
    void testAddMusician() {
        assertEquals(2, userML.getMusicians().size());

        List<Song> bSongs = new ArrayList<>();
        Song mr = new Song("My Romance", 7.12, 1);
        bSongs.add(mr);
        Musician bill = new Musician("Bill Evans", bSongs);

        userML.addMusician(bill);
        assertEquals(3, userML.getMusicians().size());
        assertEquals(bill, userML.getMusicians().get(2));

        List<Song> tiSongs = new ArrayList<>();
        Song ae = new Song("Alter Ego", 4.49, 17);
        Song lih = new Song("Let It Happen", 7.48, 5);
        tiSongs.add(ae);
        tiSongs.add(lih);
        Musician tameImpala = new Musician("Tame Impala", tiSongs);

        userML.addMusician(tameImpala);
        assertEquals(4, userML.getMusicians().size());
        assertEquals(tameImpala, userML.getMusicians().get(3));

    }

    @Test
    void testGetMostHeardMusicianThenMoreHeardMusician() {
        assertEquals(taylor, userML.getMostHeardMusician());

        List<Song> bSongs = new ArrayList<>();
        Song mr = new Song("My Romance", 7.12, 29);
        bSongs.add(mr);
        Musician bill = new Musician("Bill Evans", bSongs);
        userML.addMusician(bill);

        assertEquals(bill, userML.getMostHeardMusician());
    }

    @Test
    void testGetMostHeardMusicianThenMoreLessMusician() {
        assertEquals(taylor, userML.getMostHeardMusician());

        List<Song> bSongs = new ArrayList<>();
        Song mr = new Song("My Romance", 7.12, 1);
        bSongs.add(mr);
        Musician bill = new Musician("Bill Evans", bSongs);
        userML.addMusician(bill);

        assertEquals(taylor, userML.getMostHeardMusician());
    }

    @Test
    void testGetLeastHeardMusicianThenMoreHeardMusician() {
        assertEquals(kendrick, userML.getLeastHeardMusician());

        List<Song> bSongs = new ArrayList<>();
        Song mr = new Song("My Romance", 7.12, 29);
        bSongs.add(mr);
        Musician bill = new Musician("Bill Evans", bSongs);
        userML.addMusician(bill);

        assertEquals(kendrick, userML.getLeastHeardMusician());
    }

    @Test
    void testGetLeastHeardMusicianThenLessHeardMusician() {
        assertEquals(kendrick, userML.getLeastHeardMusician());

        List<Song> bSongs = new ArrayList<>();
        Song mr = new Song("My Romance", 7.12, 1);
        bSongs.add(mr);
        Musician bill = new Musician("Bill Evans", bSongs);
        userML.addMusician(bill);

        assertEquals(bill, userML.getLeastHeardMusician());
    }

    @Test   
    void testGetTotalTimeListened() {
        assertEquals(115.10, userML.getTotalTimeListened());

        List<Song> bSongs = new ArrayList<>();
        Song mr = new Song("My Romance", 7.12, 1);
        bSongs.add(mr);
        Musician bill = new Musician("Bill Evans", bSongs);
        userML.addMusician(bill);

        assertEquals(122.22, userML.getTotalTimeListened());
    }

    @Test
    void testFindMusician() {
        assertEquals(kendrick, userML.findMusician("Kendrick Lamar"));
        assertEquals(taylor, userML.findMusician("Taylor Swift"));
        assertNull(userML.findMusician("Miles Davis"));
    }

    @Test
    void testIsMusicianFound() {
        assertTrue(userML.isMusicianFound("Kendrick Lamar"));
        assertTrue(userML.isMusicianFound("Taylor Swift"));
        assertFalse(userML.isMusicianFound("Nirvana"));
    }


    @Test
    void testGetTimeListenedFromMusician() {
        assertEquals(6.27 * 6, userML.getTimeListenedMusician("Kendrick Lamar"));
        assertEquals(77.48, userML.getTimeListenedMusician("Taylor Swift"));
        assertEquals(0, userML.getTimeListenedMusician("Kenny Beats"));
    }

}
