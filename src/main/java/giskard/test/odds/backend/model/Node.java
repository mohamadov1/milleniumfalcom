package giskard.test.odds.backend.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that represents a planet node in the graph
 */
@Slf4j
@Data
public class Node {

    private String name;
    private List<Destination> destinations;


    public void addDestination(String destination, int distance) {
        destinations.add(new Destination(destination, distance));
    }

    public Node(String name) {
        this.name = name;
        destinations = new ArrayList<>();
    }

    public List<String> getDestinationNameList() {
        List<String> result = new ArrayList<>();
        for(Destination destination : destinations) {
            result.add(destination.getDestinationName());
        }

        return result;
    }

    public int getCost(String node) {
        if(this.getName().equals(node)) {
            return 1;
        }

        int result = 0;
        for(Destination destination : destinations) {
            if(node.equals(destination.getDestinationName())) {
                result = destination.getDistance();
            }
        }
        return result;
    }
}