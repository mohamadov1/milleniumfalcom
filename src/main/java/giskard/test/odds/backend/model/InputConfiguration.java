package giskard.test.odds.backend.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Class that represents an instance of the input JSON configuration file
 */
@Slf4j
@Data
public class InputConfiguration {


    private int autonomy;
    private String departure;
    private String arrival;
    private String routes_db;

    @Override
    public String toString() {
        return "InputConfiguration{" +
                "autonomy=" + autonomy +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", routes_db='" + routes_db + '\'' +
                '}';
    }
}
