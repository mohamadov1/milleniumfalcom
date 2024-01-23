package giskard.test.odds.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import giskard.test.odds.backend.model.EmpireInterceptions;
import giskard.test.odds.backend.model.Path;
import giskard.test.odds.backend.utils.ConfigurationParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller that receive the JSON file containing the plans of the Empire
 */
@RestController
@RequestMapping("/c3po")
public class Controller {

    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping("/file")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> handleInterceptions(@RequestParam("filePath") String filePath) {
        try {
            String content = Files.readString(Paths.get(filePath));
            EmpireInterceptions interceptions = objectMapper.readValue(content, EmpireInterceptions.class);
            return ResponseEntity.ok(handleInterceptions(interceptions));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la manipulation du fichier.");
        }
    }

    @PostMapping("/intercept")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Map<String, Object>>> handleInterceptions(@RequestBody EmpireInterceptions interceptions) {
        System.out.println("intercepting empire.json");

        Map<Path, Double> odds = ConfigurationParser.calculateOdds(interceptions);

        List<Map<String, Object>> resultsList = new ArrayList<>();

        if (!odds.isEmpty()) {
            int minIndex = findIndexOfMinimumOdd(odds);
            Path path = odds.keySet().stream().toList().get(minIndex);

            Map<String, Object> result = new HashMap<>();
            result.put("path", path.toString());
            result.put("cost", path.getCost());
            result.put("odds", odds.get(path));

            resultsList.add(result);
        }

        System.out.println("Results: " + resultsList);

        return ResponseEntity.ok(resultsList);
    }

    private int findIndexOfMinimumOdd(Map<Path, Double> odds) {
        Double max = Double.valueOf(0);
        int i = 0;
        int maxIndex = 0;
        for(Double odd : odds.values()) {
            if(odd > max) {
                maxIndex = i;
                max = odd;
            }
            i++;
        }
        return maxIndex;
    }



}
