package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class SongTest {

    private Song alterEgo;

    @BeforeEach
    void setup() {
        alterEgo = new Song("Alter Ego", 4.49, 17);
    }

    @Test
    void testConstructor() {
        assertEquals("Alter Ego", alterEgo.getName());
        assertEquals(4.49, alterEgo.getSongLength());
        assertEquals(17, alterEgo.getTimesPlayed());
    }

    @Test
    void testAddTimesPlayed() {
        alterEgo.addTimesPlayed(1);
        assertEquals(18, alterEgo.getTimesPlayed());
        alterEgo.addTimesPlayed(5);
        assertEquals(23, alterEgo.getTimesPlayed());
    }

    @Test
    void testGetTotalTimeListened() {
        assertEquals(17 * 4.49, alterEgo.getTotalTimeListened());

        alterEgo.addTimesPlayed(1);
        assertEquals(18, alterEgo.getTimesPlayed());
        assertEquals(18 * 4.49, alterEgo.getTotalTimeListened());

        alterEgo.addTimesPlayed(5);
        assertEquals(23, alterEgo.getTimesPlayed());
        assertEquals(23 * 4.49, alterEgo.getTotalTimeListened());
    }





}
