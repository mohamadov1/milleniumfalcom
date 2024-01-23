package giskard.test.odds.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Class that represents the intercepted plan of the Empire
 */
@Slf4j
@Data
@AllArgsConstructor
public class EmpireInterceptions {

    private int countdown;
    private List<BountyHunter> bounty_hunters;

}
