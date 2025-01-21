package vttp5a.paf.day26l.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp5a.paf.day26l.model.Song;
import vttp5a.paf.day26l.repository.SongRepository;

@Service
public class SongService {
    
    @Autowired
    private SongRepository songRepository;

    public List<Integer> getYears() {
        List<Integer> yearsList = songRepository.getYears();
        yearsList.sort(Comparator.reverseOrder());
        return yearsList;
    }

    public List<Song> getSongsByYear(Integer year) {
        List<Song> songsList = new ArrayList<>();

        List<Document> songs = songRepository.getSongsByYear(year);
        for (int i = 0; i < songs.size(); i++) {
            Document d = songs.get(i);
            Song s = Song.toSong(d);
            songsList.add(s);
        }

        return songsList;
    }
}
