package giskard.test.odds;

import giskard.test.odds.backend.model.*;
import giskard.test.odds.backend.utils.ConfigurationParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

import static giskard.test.odds.backend.utils.ConfigurationParser.getAllPaths;
import static giskard.test.odds.backend.utils.ConfigurationParser.readGraphFromDb;

/**
 * Main class
 */

@Slf4j
@SpringBootApplication
public class Application {

	public static List<Path> paths = new ArrayList<>();

	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("Argument count must be 1, had: " + args.length);
			System.exit(1);
		}
		String db_path = args[0];

		InputConfiguration inputConfiguration = ConfigurationParser.readConfiguration(db_path);

		Graph graph = readGraphFromDb(inputConfiguration.getRoutes_db());

		getAllPaths(inputConfiguration, graph);

		SpringApplication.run(Application.class, args);
	}

}
