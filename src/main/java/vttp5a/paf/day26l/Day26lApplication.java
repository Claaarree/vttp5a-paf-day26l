package vttp5a.paf.day26l;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp5a.paf.day26l.repository.SeriesRepository;

@SpringBootApplication
public class Day26lApplication implements CommandLineRunner{

	@Autowired
	private SeriesRepository seriesRepository;

	public static void main(String[] args) {
		SpringApplication.run(Day26lApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Execute the query
		List<Document> result = seriesRepository.findSeriesByName("love");

		result.stream()
			.limit(3)
			.forEach(d-> {
				// System.out.println(">>>>>" + d.toJson());
				System.out.printf("Name: %s\n", d.getString("name"));
				// genres: ["a", "b", "c"]
				List<String> genres = d.getList("genres", String.class);
				for (String g : genres) {
					System.out.printf("%s ", g);
				}
				System.out.println();

				// schedule:{ time: "20:30"}
				Document schedule = (Document)d.get("schedule");
				System.out.printf("time: %s\n".formatted(schedule.getString("time")));
			});

	}

}
