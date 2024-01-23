package giskard.test.odds.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Class that represents an adjacent node in the graph
 */
@Slf4j
@Data
@AllArgsConstructor
public class Destination {


    private String destinationName;
    private int distance;

}