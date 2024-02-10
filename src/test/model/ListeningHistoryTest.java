package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ListeningHistoryTest {

    private ListeningHistory userLH;
    private List<Musician> musicians;
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
        mt = new Song("Money Trees", 6.27, 6);
        kSongs = new ArrayList<>();
        kSongs.add(mt);
        kendrick = new Musician("Kendrick Lamar", kSongs);

        ah = new Song("Anti-Hero", 3.23, 1);
        bs = new Song("Blank Space", 3.51, 12);
        ls = new Song("Love Story", 3.57, 9);
        tSongs = new ArrayList<Song>();
        tSongs.add(ah);
        tSongs.add(bs);
        tSongs.add(ls);
        taylor = new Musician("Taylor Swift", tSongs);

        musicians = new ArrayList<Musician>();
        musicians.add(kendrick);
        musicians.add(taylor);
        userLH = new ListeningHistory("Miguel", musicians);

    }

    @Test
    void testConstructor() {
        assertEquals("Miguel", userLH.getName());
        assertEquals(musicians, userLH.getMusiciansHeard());
    }

    @Test
    void testAddMusician() {
        assertEquals(2, userLH.getMusiciansHeard().size());

        List<Song> bSongs = new ArrayList<>();
        Song mr = new Song("My Romance", 7.12, 1);
        bSongs.add(mr);
        Musician bill = new Musician("Bill Evans", bSongs);

        userLH.addMusician(bill);
        assertEquals(3, userLH.getMusiciansHeard().size());
        assertEquals(bill, userLH.getMusiciansHeard().get(2));

        List<Song> tiSongs = new ArrayList<>();
        Song ae = new Song("Alter Ego", 4.49, 17);
        Song lih = new Song("Let It Happen", 7.48, 5);
        tiSongs.add(ae);
        tiSongs.add(lih);
        Musician tameImpala = new Musician("Tame Impala", tiSongs);

        userLH.addMusician(tameImpala);
        assertEquals(4, userLH.getMusiciansHeard().size());
        assertEquals(tameImpala, userLH.getMusiciansHeard().get(3));

    }

    @Test
    void testGetMostHeardMusicianThenMoreHeardMusician() {
        assertEquals(taylor, userLH.getMostHeardMusician());

        List<Song> bSongs = new ArrayList<>();
        Song mr = new Song("My Romance", 7.12, 29);
        bSongs.add(mr);
        Musician bill = new Musician("Bill Evans", bSongs);
        userLH.addMusician(bill);

        assertEquals(bill, userLH.getMostHeardMusician());
    }


    @Test
    void testGetMostHeardMusicianThenMoreLessMusician() {
        assertEquals(taylor, userLH.getMostHeardMusician());

        List<Song> bSongs = new ArrayList<>();
        Song mr = new Song("My Romance", 7.12, 1);
        bSongs.add(mr);
        Musician bill = new Musician("Bill Evans", bSongs);
        userLH.addMusician(bill);

        assertEquals(taylor, userLH.getMostHeardMusician());
    }

    @Test
    void testGetLeastHeardMusicianThenMoreHeardMusician() {
        assertEquals(kendrick, userLH.getLeastHeardMusician());

        List<Song> bSongs = new ArrayList<>();
        Song mr = new Song("My Romance", 7.12, 29);
        bSongs.add(mr);
        Musician bill = new Musician("Bill Evans", bSongs);
        userLH.addMusician(bill);

        assertEquals(kendrick, userLH.getLeastHeardMusician());
    }


    @Test
    void testGetLeastHeardMusicianThenLessHeardMusician() {
        assertEquals(kendrick, userLH.getLeastHeardMusician());

        List<Song> bSongs = new ArrayList<>();
        Song mr = new Song("My Romance", 7.12, 1);
        bSongs.add(mr);
        Musician bill = new Musician("Bill Evans", bSongs);
        userLH.addMusician(bill);

        assertEquals(bill, userLH.getLeastHeardMusician());
    }

    @Test   
    void testGetTotalTimeListened() {
        assertEquals(115.10, userLH.getTotalTimeListened());

        List<Song> bSongs = new ArrayList<>();
        Song mr = new Song("My Romance", 7.12, 1);
        bSongs.add(mr);
        Musician bill = new Musician("Bill Evans", bSongs);
        userLH.addMusician(bill);

        assertEquals(122.22, userLH.getTotalTimeListened());
    }

    @Test
    void testFindMusician() {
        assertEquals(kendrick, userLH.findMusician("Kendrick Lamar"));
        assertEquals(taylor, userLH.findMusician("Taylor Swift"));
    }


    @Test
    void testGetTimeListenedFromMusician() {
        assertEquals(6.27 * 6, userLH.getTimeListenedFromMusician("Kendrick Lamar"));
        assertEquals(77.48, userLH.getTimeListenedFromMusician("Taylor Swift"));
    }

}
