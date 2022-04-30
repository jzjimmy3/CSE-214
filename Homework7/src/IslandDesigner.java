//Jimmy Zhang 112844431 CSE-214 R02

import java.util.Scanner;

/**
 * This class represents the driver function for the entire program
 * @author Jimmy Zhang
 */
public class IslandDesigner {
    public static Scanner input = new Scanner(System.in);
    public static boolean quitValue = false;
    public static void main(String[] args) throws Exception {

        loadMap();
        mainMenu();
        while(!quitValue){
            selectMenu();
        }
    }
    /**
     * The functionality below loads the map
     */
    public static void loadMap() throws Exception {
        IslandNetwork.loadFromFile();
    }
    /**
     * The method below displays the main menu options
     */
    public static void mainMenu(){
        System.out.println("Menu:\n" +
                "    D) Destinations reachable (Depth First Search)\n" +
                "    F) Maximum Flow\n" +
                "    S) Shortest Path (Extra Credit)\n" +
                "    Q) Quit");
    }
    /**
     * The method below allows users to select the menu option
     */
    public static void selectMenu(){
        System.out.print("Please select an option: ");
        switch (input.next().toUpperCase()){
            case "D" : destination();
                break;
            case "F" : maxFlow();
                break;
            case "S" : shortestPath();
                break;
            case "Q" : quit();
                break;
            default:
                System.out.println("Try Again");
                break;
        }
    }
    /**
     * The method below implements the DFS functionality
     */
    private static void destination() {
        IslandNetwork graph = new IslandNetwork(IslandNetwork.vertices);
        for(int i = 0; i < IslandNetwork.getStr1().size();i++){
            graph.addEdge(IslandNetwork.getStr1().get(i),IslandNetwork.getStr3().get(i));
        }
        System.out.println("DFS Starting From: ");
        graph.DFS("University");
        System.out.println();
        graph.DFS("South Spoon");
        System.out.println();
    }
    /**
     * The method below implements the maxFlow functionality
     */
    private static void maxFlow() {
        IslandNetwork graph = new IslandNetwork(IslandNetwork.vertices);
        for(int i = 0; i < IslandNetwork.getStr1().size();i++){
            graph.addEdge(IslandNetwork.getStr1().get(i),IslandNetwork.getStr3().get(i));
        }
        graph.DFS("University", "Hipster");
    }

    /**
     * The functionality below shows the shortest path between the graph
     */
    private static void shortestPath() {
    }

    /**
     * The method below exits the program
     */
    private static void quit() {
        System.out.println("You can go your own way! Goodbye!");
        quitValue = true;
    }
}
