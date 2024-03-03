package persistence;

import model.MusicLibrary;
import model.Musician;
import model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.JsonWriter;
import persistance.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests were made with the help of the reference project "JsonSerializationDemo",
// from the University of British Columbia's CPSC 210 course.
public class JsonWriterTest extends JsonTest{

    MusicLibrary ml;

    @BeforeEach
    void setUp() {
        List<Musician> musicians = new ArrayList<>();
        ml = new MusicLibrary(musicians);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Expected IOException is thrown");
        } catch (IOException e) {
            // expected exception catch
        }
    }

    @Test
    void testWriterEmptyMusicLibrary() {
        try {
            String source = "./data/testEmptyMusicLibrary.json";
            JsonWriter writer = new JsonWriter(source);
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader(source);
            ml = reader.read();
            assertEquals(0, ml.getMusicians().size());

        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testWriterGeneralMusicLibrary() {
        try {
            addMusicians();
            String source = "./data/testGeneralMusicLibrary.json";
            JsonWriter writer = new JsonWriter(source);
            writer.open();
            writer.write(ml);
            writer.close();

            JsonReader reader = new JsonReader(source);
            ml = reader.read();
            List<Musician> musicians = ml.getMusicians();
            assertEquals(2, musicians.size());
            checkMusician("Anri", getAnriSongs(), musicians.get(0));

            checkMusician("John Mayer", getJohnMayerSongs(), musicians.get(1));
        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

    void addMusicians() {
        List<Song> anriSongs = getAnriSongs();
        Musician anri = new Musician("Anri", anriSongs);
        ml.addMusician(anri);

        List<Song> jmSongs = getJohnMayerSongs();
        Musician johnMayer = new Musician("John Mayer", jmSongs);
        ml.addMusician(johnMayer);
    }



}
