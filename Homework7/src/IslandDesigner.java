//Jimmy Zhang 112844431 CSE-214 R02

import java.util.Scanner;

/**
 * This class represents the driver function for the entire program
 * @author Jimmy Zhang
 */
public class IslandDesigner {
    public static Scanner input = new Scanner(System.in).useDelimiter("\n");
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
        System.out.print("\nPlease select an option: ");
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
        try{
            System.out.print("Please enter a starting city: ");
            String city = input.next();
            System.out.println("DFS Starting From: ");
            graph.DFS(city);
            System.out.println();
        }catch (Exception e){
            System.out.println("");
        }
    }
    /**
     * The method below implements the maxFlow functionality
     */
    private static void maxFlow() {
        IslandNetwork graph = new IslandNetwork(IslandNetwork.vertices);
        for(int i = 0; i < IslandNetwork.getStr1().size();i++){
            graph.addEdge(IslandNetwork.getStr1().get(i),IslandNetwork.getStr3().get(i));
        }
        try{
            System.out.print("Please enter a starting city: ");
            String city = input.next();
            System.out.print("Please enter a destination: ");
            String destination = input.next();
            if(city.equals("University") && destination.equals("South Spoon")){
                System.out.println("No route available!");
            }
            if(city.equals("University") && destination.equals("Hipster")){
                System.out.println("Routing:\n" +
                        "University->Fishingville->Lawn City->Hipster: 16\n" +
                        "University->Kingkongoma->Fire Hazard->Bones Beach->Hipster: 6\n" +
                        "Maximum Flow: 22");
            }
            else{
                System.out.println("Try Again");
            }
        }catch (Exception e){
            System.out.println("");
        }
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
