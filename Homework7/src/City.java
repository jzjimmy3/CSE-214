import java.util.HashMap;

public class City {
    public HashMap<String,Integer> neighbors = new HashMap<>();
    public String name;
    public String source;
    public String destination;
    int weight;
    public City(String source, String destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }


}
