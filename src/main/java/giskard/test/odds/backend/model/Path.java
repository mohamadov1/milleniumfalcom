package giskard.test.odds.backend.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Class that represents a solution path to the destination
 */
@Slf4j
@Data
public class Path {

    private List<Node> nodes;


    private int cost;
    public Path(List<Node> nodes, int cost) {
        this.nodes = nodes;
        this.cost = cost;
    }

    @Override
    public String toString() {
        String showNodes = "[ ";
        for(Node node : nodes) {
            showNodes += node.getName() + ", ";
        }
        showNodes += "]";
        return showNodes;
    }

}