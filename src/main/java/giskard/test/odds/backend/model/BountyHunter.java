package giskard.test.odds.backend.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Class that represents an interception of one day in the plan of the Empire
 */
@Slf4j
@Data
public class BountyHunter {
    private String planet;
    private int day;
}
