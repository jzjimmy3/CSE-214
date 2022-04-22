import big.data.DataSource;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Scanner;

public class IslandDesigner {
    public static Scanner input = new Scanner(System.in);
    public static boolean quitValue = false;

    public static void main(String[] args) throws Exception {
        mainMenu();
        loadMap();
        while(!quitValue){
            selectMenu();
        }
    }
    public static void loadMap() throws Exception {
        HashMap<String, Node> cities = new HashMap<String,Node>();
        DataSource ds = DataSource.connectXML("https://www.cs.stonybrook.edu/~cse214/hw/hw7-images/hw7.xml");
        ds.load();
        String cityNamesStr=ds.fetchString("cities");
        String[] cityNames=cityNamesStr.substring(1,cityNamesStr.length()-1).replace("\"","").split(",");
        String roadNamesStr=ds.fetchString("roads");
        String[] roadNames=roadNamesStr.substring(1,roadNamesStr.length()-1).split("\",\"");
        for(int i = 0; i < cityNames.length; i++){
            System.out.println(cityNames[i]);
        }
        System.out.println("--------------------");
        for(int i = 0; i < roadNames.length; i++){
            System.out.println(roadNames[i]);
        }
    }

    public static void mainMenu(){
        System.out.println("Menu:\n" +
                "    D) Destinations reachable (Depth First Search)\n" +
                "    F) Maximum Flow\n" +
                "    S) Shortest Path (Extra Credit)\n" +
                "    Q) Quit");
    }
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
            default: break;
        }
    }

    private static void destination() {
    }

    private static void maxFlow() {
    }

    private static void shortestPath() {
    }

    private static void quit() {
        System.out.println("You can go your own way! Goodbye!");
        quitValue = true;
    }
}
