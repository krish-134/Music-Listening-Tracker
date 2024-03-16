package model;

import java.util.ArrayList;
import java.util.List;
import model.Musician;
import model.MusicLibrary;

public class SearchMusic {

    private List<Song> songs;
    private List<Musician> musicians;


    public SearchMusic(List<Object> musicObjects) {
        songs = new ArrayList<>();
        musicians = new ArrayList<>();
        setMusicList(musicObjects);
    }

    public void setMusicList(List<Object> musicObjects) {
        if (!musicObjects.isEmpty()) {
            Object musicThing = musicObjects.get(0);

            if (musicThing instanceof Musician) {
                for (Object musicianObject : musicObjects) {
                    musicians.add((Musician) musicianObject);
                }

            } else if (musicThing instanceof Song) {
                for (Object songObject : musicObjects) {
                    songs.add((Song) songObject);
                }
            }
        }
    }

    // EFFECTS: returns the least played musician, assuming that no two musicians
    //          have been listened to by the exact same amount of time
    public Object getMostOrLeastHeard(boolean tryMusician, boolean tryGreater) {

        if (tryMusician) {
            Musician musicianHeard = musicians.get(0);

            for (Musician m: musicians) {
                if (tryGreater ? m.getTotalTimeListened() > musicianHeard.getTotalTimeListened() :
                        m.getTotalTimeListened() < musicianHeard.getTotalTimeListened()) {
                    musicianHeard = m;
                }
            }
            return musicianHeard;

        } else {
            Song songHeard = songs.get(0);
            for (Song s: songs) {
                if (tryGreater ? s.getTotalTimeListened() > songHeard.getTotalTimeListened() :
                        s.getTotalTimeListened() < songHeard.getTotalTimeListened()) {
                    songHeard = s;
                }
            }
            return songHeard;
        }

    }

}
