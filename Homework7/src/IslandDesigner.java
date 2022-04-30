import java.util.Scanner;

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
    public static void loadMap() throws Exception {
        IslandNetwork.loadFromFile();
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

    private static void maxFlow() {
        IslandNetwork graph = new IslandNetwork(IslandNetwork.vertices);
        for(int i = 0; i < IslandNetwork.getStr1().size();i++){
            graph.addEdge(IslandNetwork.getStr1().get(i),IslandNetwork.getStr3().get(i));
        }
        graph.DFS("University", "Hipster");
    }

    private static void shortestPath() {
    }

    private static void quit() {
        System.out.println("You can go your own way! Goodbye!");
        quitValue = true;
    }
}
