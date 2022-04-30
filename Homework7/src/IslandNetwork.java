import big.data.DataSource;
import org.w3c.dom.Node;

import java.util.*;

public class IslandNetwork {
    public HashMap<String, LinkedList<String>> adjList = new HashMap();
    public HashMap<String, Integer> indexes = new HashMap<>();
    public static ArrayList<String> vertices = new ArrayList<>();
    public static ArrayList<String> str1 = new ArrayList<>();
    public static ArrayList<String> str2 = new ArrayList<>();
    public static ArrayList<String> str3 = new ArrayList<>();
//    public static ArrayList<String> str4 = new ArrayList<>();
//    public static ArrayList<Integer> weight = new ArrayList<>();

    public static ArrayList<String> getStr1() {
        return str1;
    }

    public static ArrayList<String> getStr2() {
        return str2;
    }

    public static ArrayList<String> getStr3() {
        return str3;
    }

    public ArrayList<String> getVertices() {
        return vertices;
    }

    int index = -1;
//    public IslandNetwork() {};
    public IslandNetwork(ArrayList<String> vertices) {
        for (int i = 0; i <vertices.size() ; i++) {
            String vertex = vertices.get(i);
            LinkedList<String> list = new LinkedList<>();
            adjList.put(vertex, list);
            indexes.put(vertex, ++index);
        }
    }
    public IslandNetwork() { }
    public static IslandNetwork graphs = new IslandNetwork(vertices);

    public IslandNetwork getGraphs() {
        return graphs;
    }

    public void addEdge(String source, String destination) {
        //add forward edge
        LinkedList<String> list;
        list = adjList.get(source);
        list.addFirst(destination);
        adjList.put(source, list);
    }

    public void DFS(String sources){
        int vertices = adjList.size();
        boolean [] visited = new boolean[vertices];
        if(!visited[indexes.get(sources)]){
            DFSUtil(sources, visited);
        }
    }
    public void DFS(String sources, String Destination){
        int vertices = adjList.size();
        boolean [] visited = new boolean[vertices];
        if(visited[indexes.get(Destination)])
        if(!visited[indexes.get(sources)]){
            DFSUtil(sources, visited);
        }
    }
    public void DFSUtil(String source, boolean[] visited){
        //mark this visited
        visited[indexes.get(source)] = true;
        System.out.print(source + " ");
        LinkedList<String> list = adjList.get(source);
        for (int i = 0; i <list.size() ; i++) {
            String destination = list.get(i);
            if(!visited[indexes.get(destination)])
                DFSUtil(destination,visited);
        }
    }

    public void printGraph() {
        Set<String> set = adjList.keySet();
        Iterator<String> iterator = set.iterator();

        while(iterator.hasNext()){
            Object vertex = iterator.next();
            System.out.print("Vertex " + vertex + " is connected to: ");
            LinkedList<String> list = adjList.get(vertex);
            for (int i = 0; i <list.size() ; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }
    }

    public static void loadFromFile(){
        HashMap<String, Node> cities = new HashMap<String,Node>();
        DataSource ds = DataSource.connectXML("https://www.cs.stonybrook.edu/~cse214/hw/hw7-images/hw7.xml");
        ds.load();
        String cityNamesStr=ds.fetchString("cities");
        String[] cityNames=cityNamesStr.substring(1,cityNamesStr.length()-1).replace("\"","").split(",");
        String roadNamesStr=ds.fetchString("roads");
        String[] roadNames=roadNamesStr.substring(1,roadNamesStr.length()-1).split("\",\"");

        System.out.println("Map Loaded.\n\nCities: ");
        System.out.println("---------------------");
        Arrays.sort(cityNames);
        for(int i = 0; i < cityNames.length; i++){
            vertices.add(cityNames[i]);
            System.out.println(cityNames[i]);
        }
        System.out.println("\nRoad                                  Capacity");
        System.out.println("----------------------------------------------");
        for(int i = 0; i < roadNames.length; i++){
            roadNames[i]=roadNames[i].replace("\"", "");
            str1.add(roadNames[i].substring(0,roadNames[i].indexOf(",")));
            str2.add(roadNames[i].substring(roadNames[i].indexOf(",")+1));
            str3.add(str2.get(i).substring(0,str2.get(i).indexOf(",")));
//            str4.add(str2.get(i).substring(str2.get(i).indexOf(",")+1));
//            weight.add(Integer.parseInt(str4.get(i).replaceAll("\\s","")));
        }
        for(int i = 0; i < roadNames.length; i++){
            roadNames[i]=roadNames[i].replaceFirst(",", " to ");
            roadNames[i]=roadNames[i].replaceFirst(",", "                    ");
            System.out.println(roadNames[i]);
        }
    }
}
