package vttp5a.paf.day26l.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static vttp5a.paf.day26l.repository.Constants.*;

import java.util.List;


@Repository
public class SongRepository {
    
    @Autowired
    private MongoTemplate template;

    // db.songs.distinct("released_year")
    public List<Integer> getYears() {
        List<Integer> yearsList = template
            .findDistinct(new Query(), F_RELEASED_YEAR, C_SONGS, Integer.class);

        return yearsList;
    }

    // db.songs.find({
    //     released_year:2020
    // })
    // .projection({
    //     "artist(s)_name": 1, "track_name":1, _id:0
    // })
    // .sort({"artist(s)_name":1 , "track_name": 1})
    public List<Document> getSongsByYear(Integer year) {
        Criteria criteria = Criteria.where(F_RELEASED_YEAR)
            .is(year);

        Query query = Query.query(criteria)
                .with(Sort.by(Direction.ASC, "artist(s)_name", "track_name"));
        query.fields()
                .include("artist(s)_name", "track_name")
                .exclude("_id");

        List<Document> songs = template.find(query, Document.class, C_SONGS);

        return songs;
    }
}
