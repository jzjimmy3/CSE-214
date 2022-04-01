//Jimmy Zhang CSE 214 R02 ID: 112844431
//is Ninetndo Leaf, check the plus sign and minus sign

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The class below represents the NintendoNetwork which is the driver function
 */
public class NintendoNetwork {
    public static boolean quitValue = false;
    public static Scanner input = new Scanner(System.in);
    public static NetworkNode pasteVal = null;

    public static void main(String[] args) throws FileNotFoundException {
        networkManager();
        while(!quitValue){
            System.out.print("\nPlease select an option: ");
            selectOption();
        }
    }

    /**
     * This function outputs the menuOptions
     */
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

    /**
     * This function allows user to select options
     * @throws FileNotFoundException
     */
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

    /**
     * The function below loads the files
     * @throws FileNotFoundException
     */
    private static void load() throws FileNotFoundException {
//        System.out.print("Please enter filename: ");
//        String inputVal = input.next();
        NetworkTree networkTree = new NetworkTree();
        networkTree.readFromFile("sbutopology.txt");
    }

    /**
     * The function below prints the file
     */
    private static void print() {
        System.out.println(NetworkTree.networkTree);
    }

    /**
     * The function below moves the cursor to child
     */
    private static void cursorToChild() {
        System.out.println("PLease choose an index");
        NetworkTree.networkTree.cursorToChild(input.nextInt());
    }
    /**
     * The function below adds a child
     */
    private static void addChild() {
        NetworkTree.networkTree.addChild(3,new NetworkNode("HEER", false));

    }

    /**
     * The function below moves the cursor to the partner
     */
    private static void cursorUp() {
        NetworkTree.networkTree.cursorToParent();
    }

    /**
     * The function below removes the node and all of its children
     */
    private static void cut() {
        pasteVal = NetworkTree.networkTree.cutCursor();
    }

    /**
     * The function below pastes the node and all of its children
     */
    private static void paste() {
//        NetworkTree.networkTree.getCursor().setChildren(pasteVal.getChildren());
    }

    /**
     * The function below moves the cursor to roots
     */
    private static void cursorToRoot() {
        NetworkTree.networkTree.cursorToRoot();
    }
    /**
     * The function below saves the binary tree to a text file
     */
    private static void save() {
    }

    private static void cursorToMinimal() {
    }

    private static void cursorBroken() {
    }

    /**
     * The function below exits the program
     */
    public static void quit(){
        System.out.println("Make like a tree and leave!");
        quitValue = true;
    }
}
