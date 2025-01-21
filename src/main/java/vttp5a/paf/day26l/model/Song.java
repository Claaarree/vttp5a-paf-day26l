package vttp5a.paf.day26l.model;

import org.bson.Document;

public class Song {
    private String artistName;
    private String trackName;

    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public String getTrackName() {
        return trackName;
    }
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public static Song toSong(Document d) {
        Song s = new Song();
        String artistName = d.getString("artist(s)_name");
        // System.out.println(d.get("track_name"));
        Object o = d.get("track_name");
        if(o instanceof String){
            s.setTrackName((String)o);
        } else{
            s.setTrackName(String.valueOf(o));
        }
     
        s.setArtistName(artistName);

        
        return s;
    }
}
