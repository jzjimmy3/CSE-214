//Jimmy Zhang 112844431 CSE-214 R02

import java.util.HashMap;

/**
 * This class represents the citt class for the assiment. It is where the verticies and indexes are implemented.
 * @author Jimmy Zhang
 */
public class City {
    public HashMap<String,Integer> neighbors = new HashMap<>();
    public String name;
    public String source;
    public String destination;
    int weight;
    /**
     * The constructor below constructs the city for and class that implements this functionality.
     */
    public City(String source, String destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * The Method below represents getter and setter methods for the class
     */

    public HashMap<String, Integer> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(HashMap<String, Integer> neighbors) {
        this.neighbors = neighbors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
