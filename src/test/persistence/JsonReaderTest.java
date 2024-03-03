package persistence;

import model.MusicLibrary;
import model.Musician;
import org.junit.jupiter.api.Test;

import persistance.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests were made with the help of the reference project "JsonSerializationDemo",
// from the University of British Columbia's CPSC 210 course.
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNoFileFound() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            MusicLibrary ml = reader.read();
            fail("Expected IOException is thrown");
        } catch (IOException e) {
            // exception is expected
        }
    }

    @Test
    void testReaderEmptyMusicLibrary() {
        JsonReader reader = new JsonReader("./data/testEmptyMusicLibrary.json");
        try {
            MusicLibrary ml = reader.read();
            assertEquals(0, ml.getMusicians().size());
        } catch (IOException e) {
            fail("Unable to read from file");
        }
    }

    @Test
    void testReaderGeneralMusicLibrary() {
        JsonReader reader = new JsonReader("./data/testGeneralMusicLibrary.json");
        try {
            MusicLibrary ml = reader.read();
            List<Musician> musicians = ml.getMusicians();
            assertEquals(2, musicians.size());
            checkMusician("Anri", getAnriSongs(), musicians.get(0));
            checkMusician("John Mayer", getJohnMayerSongs(), musicians.get(1));
        } catch (IOException e) {
            fail("Unable to read from file");
        }
    }


}
