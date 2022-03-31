import java.io.FileNotFoundException;
import java.util.Scanner;

public class NintendoNetwork {
    public static boolean quitValue = false;
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        networkManager();
        while(!quitValue){
            System.out.print("\nPlease select an option: ");
            selectOption();
        }
    }
    public static void networkManager(){
        System.out.println("Welcome to the Nintendo Network Manager.\n" +
                "\nMenu:\n" +
                "    L) Load from file\n" +
                "    P) Print tree\n" +
                "    C) Move cursor to a child node\n" +
                "    R) Move cursor to root\n" +
                "    U) Move cursor up to parent\n" +
                "    A) Add a child //if tree is empty, do not prompt for child index number\n" +
                "    X) Remove/Cut Cursor and its subtree\n" +
                "    V) Paste Cursor and its subtree\n" +
                "    S) Save to file\n" +
                "    M) Cursor to root of minimal subtree containing all faults\n" +
                "    B) Mark cursor as broken/fixed\n" +
                "    Q) Quit");
    }
    public static void selectOption() throws FileNotFoundException {
        switch(input.next().toUpperCase()){
            case "L" : load();
                break;
            case "P" : print();
                break;
            case "C" : cursorToChild();
                break;
            case "A" : addChild();
                break;
            case "U" : cursorUp();
                break;
            case "X" : cut();
                break;
            case "V" : paste();
                break;
            case "R" : cursorToRoot();
                break;
            case "S" : save();
                break;
            case "M" : cursorToMinimal();
                break;
            case "B" : cursorBroken();
                break;
            case "Q" : quit();
                break;
            default:
                System.out.println("Try again");
        }
    }

    private static void load() throws FileNotFoundException {
//        System.out.print("Please enter filename: ");
//        String inputVal = input.next();
        NetworkTree networkTree = new NetworkTree();
        networkTree.readFromFile("sbutopology.txt");
        System.out.println(NetworkTree.networkTree);
    }

    private static void print() {
    }

    private static void cursorToChild() {
    }

    private static void addChild() {
    }

    private static void cursorUp() {
    }

    private static void cut() {
    }

    private static void paste() {
    }

    private static void cursorToRoot() {
    }

    private static void save() {
    }

    private static void cursorToMinimal() {
    }

    private static void cursorBroken() {
    }

    public static void quit(){
        System.out.println("Make like a tree and leave!");
        quitValue = true;
    }
}
