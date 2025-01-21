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
public class SeriesRepository {
    
    @Autowired
    private MongoTemplate template;

    // MUST WRITE BELOW IN EXAM! WILL HAVE MARKS
    // db.series.find({
    //     name: {
    //         $regex: 'a name',
    //         $options:"i"
    //     }
    // })
    public List<Document> findSeriesByName(String name) {
        // Create the condition
        Criteria criteria = Criteria.where(F_NAME)
                .regex(name, "i");

        // Create the query using the predicate/condition
        Query query = Query.query(criteria);
        
        // Below is projections
        query.fields()
                .include("name", "id", "summary", "image.original", "genres", "schedule")
                .exclude("_id");

        // Perform the query
        // take the bson Document!
        // entityClass is what the query returns us 
        List<Document> results =template.find(query, Document.class, "series");

        return results;

    }

    // db.series.find({
    //     "rating.average": {$gte: 8}
    // })
    // .sort({'rating.average': -1})
    // .limit(10)
    public List<Document> findSeriesByRating(float rating) {
        Criteria criteria = Criteria.where(F_RATING_AVERAGE)
                .gte(rating);

        Query query = Query.query(criteria)
                .with(Sort.by(Direction.DESC, F_RATING_AVERAGE))
                .limit(5);

        // Below is projections
        query.fields()
                .include("name", "id", "summary", "image.original")
                .exclude("_id");

        // JsonArray would be List
        // JsonObject is Document
        return template.find(query, Document.class, C_SERIES);
    }

    // db.series.distinct('type')

    public List<String> findTypeOfSeries() {
        return template.findDistinct(
            new Query(), F_TYPE, C_SERIES, String.class
        );
    }
}
