package persistence;

import model.Musician;
import model.Song;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkMusician(String name, List<Song> songs, Musician musician) {
        assertEquals(name, musician.getName());

        int i = 0;
        for (Song s : musician.getSongs()) {
            Song testSong = songs.get(i);
            assertEquals(testSong.getName(), s.getName());
            assertEquals(testSong.getSongLength(), s.getSongLength());
            assertEquals(testSong.getTimesPlayed(), s.getTimesPlayed());
            i++;
        }
    }

    protected List<Song> getAnriSongs() {
        List<Song> anriSongs = new ArrayList<>();
        Song catsEye = new Song("Cat's Eye", 3.1, 4);
        anriSongs.add(catsEye);
        return anriSongs;
    }

    protected List<Song> getJohnMayerSongs() {
        List<Song> jmSongs = new ArrayList<>();
        Song newLight = new Song("New Light", 3.62, 3);
        Song neon = new Song("Neon", 4.23, 5);
        jmSongs.add(newLight);
        jmSongs.add(neon);
        return jmSongs;
    }

}
