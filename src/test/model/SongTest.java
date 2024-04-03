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
    void testInvalidConstructor() {
        Song song;
        try {
            song = new Song("", 3.51, 12);
            fail("exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Song name cannot be empty", e.getMessage());
        }

        try {
            song = new Song(null, 3.51, 12);
            fail("exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Song name cannot be empty", e.getMessage());
        }

        try {
            song = new Song("aaa", 0, 12);
            fail("exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Song length cannot be less than or equal to zero", e.getMessage());
        }

        try {
            song = new Song("aaa", 2, -1);
            fail("exception should have been thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Times played cannot be less than or equal to zero", e.getMessage());
        }
    }

    @Test
    void testAddTimesPlayed() {
        alterEgo.addTimesPlayed(1);
        assertEquals(18, alterEgo.getTimesPlayed());
        alterEgo.addTimesPlayed(5);
        assertEquals(23, alterEgo.getTimesPlayed());
        alterEgo.addTimesPlayed(0);
        assertEquals(23, alterEgo.getTimesPlayed());
        try {
            alterEgo.addTimesPlayed(-5);
            fail("Should have thrown exception");
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot add -5 on to times played", e.getMessage());
        }
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
