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

    public static void main(String[] args) throws Exception {
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
    public static void selectOption() throws Exception {
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
    private static void load() throws Exception {
        System.out.print("Please enter filename: ");
        String inputVal = input.next();
        NetworkTree networkTree = new NetworkTree();
        try{
            networkTree.readFromFile(inputVal);
            System.out.println(inputVal + " loaded.");
        }catch (Exception e){
            System.out.println(inputVal + " not found.");
        }
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
        try{
            System.out.print("PLease choose an index: ");
            NetworkTree.networkTree.cursorToChild(input.nextInt());
        }catch (Exception e){
            System.out.println("Unable to move cursor to child. Please Try Again.");
        }
    }
    /**
     * The function below adds a child
     */
    private static void addChild() {
        Boolean isNintendo = false;
        try{
            System.out.print("Please enter an index: ");
            int index = input.nextInt();
            System.out.print("Please enter device name: ");
            String device = input.next();
            System.out.print("Is this Nintendo(y/n): ");
            Character nintendo = input.next().charAt(0);
            if(Character.toUpperCase(nintendo) == 'Y'){
                isNintendo = true;
            }else if(Character.toUpperCase(nintendo) == 'N'){
                isNintendo = false;
            }else{
                throw new Exception("Invalid Input. Try Again. ");
            }
            NetworkTree.networkTree.addChild(index,new NetworkNode(device, isNintendo, false));
            System.out.println("Nintendo added");
        }catch (Exception e){
            System.out.println("Unable to add child. Please Try Again.");
        }
    }

    /**
     * The function below moves the cursor to the partner
     */
    private static void cursorUp() {
        try{
            NetworkTree.networkTree.cursorToParent();
        }catch(Exception e){
            System.out.println("Unable to move cursor to parent. Please Try Again.");
        }
    }

    /**
     * The function below removes the node and all of its children
     */
    private static void cut() {
        try{
            pasteVal = NetworkTree.networkTree.cutCursor();
        }catch(Exception e){
            System.out.println("Unable to cut cursor. Please Try Again.");
        }
    }

    /**
     * The function below pastes the node and all of its children
     */
    private static void paste() {
        try{
            System.out.print("Please enter an index: ");
            int inputs = input.nextInt();
            NetworkTree.networkTree.pasteChild(inputs,pasteVal);
        }catch(Exception e){
            System.out.println("Unable to paste cursor. Please Try Again.");
        }
    }

    /**
     * The function below moves the cursor to roots
     */
    private static void cursorToRoot() {
        try{
            NetworkTree.networkTree.cursorToRoot();
        }catch(Exception e){
            System.out.println("Unable to paste cursor. Please Try Again.");
        }
    }
    /**
     * The function below saves the binary tree to a text file
     */
    private static void save() throws Exception {
        try{
            String fileName = input.next();
            NetworkTree.networkTree.writeToFile(NetworkTree.networkTree, fileName);
            System.out.println("File Saved, you have now exited the program");
            quitValue = true;
        }catch (Exception e){
            System.out.println("Unable to save file. Please Try Again");
        }
    }

    private static void cursorToMinimal() {
        try{
            NetworkTree.networkTree.cursorToMinimalBrokenSubtree();
        }catch (Exception e){
            System.out.println("Unable to move cursor to minimal. Please Try Again");
        }
    }

    private static void cursorBroken() {
        try{
            NetworkTree.networkTree.cursorBrokenStatus();
        } catch (Exception e){
            System.out.println("Unable to change cursor status. Please Try Again");
        }
    }

    /**
     * The function below exits the program
     */
    public static void quit(){
        System.out.println("Make like a tree and leave!");
        quitValue = true;
    }
}
