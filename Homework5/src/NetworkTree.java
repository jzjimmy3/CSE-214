//Jimmy Zhang CSE 214 R02 ID: 112844431

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * The class below represents the NetworkTree
 * The main parameter are root and cursor, which are both Network Nodes
 * @author Jimmy Zhang
 */
public class NetworkTree {
    public static NetworkTree networkTree = new NetworkTree();
    public static Scanner input = new Scanner(System.in);

    private NetworkNode root;
    private static NetworkNode cursor;

    public NetworkNode getRoot() {
        return root;
    }

    public void setRoot(NetworkNode root) {
        this.root = root;
    }

    public static NetworkNode getCursor() {
        return cursor;
    }

    public static void setCursor(NetworkNode cursor) {
        NetworkTree.cursor = cursor;
    }

    /**
     * The function below represents the constructor for the NetworkTree
     */
    public NetworkTree(){};
    public NetworkTree(NetworkNode root, NetworkNode cursor) {
        this.root = root;
        this.cursor = cursor;
    }
    public NetworkNode[] networkNodeDepth1 = new NetworkNode[9];

    /**
     * The function below moves the cursor node to the root of the tree.
     */
    public void cursorToRoot(){
        if(networkTree.root != null){
            cursor = networkTree.root;
            System.out.println("Cursor moved to " + cursor.getName().substring(findDepth(cursor.getName())));
        }
    }
    public NetworkNode cutCursor(){
        if(root ==  null) return null;
        NetworkNode temp2= cursor;
        NetworkNode temp = cursor;
        for(int i = 0; i < temp.getChildren().length; i++){
            temp.getChildren()[i] = null;
        }
        //remove node
        for(int i = 0; i < temp.getParent().getChildren().length; i++){
            if(temp.getParent().getChildren()[i] == cursor){
                temp.getParent().getChildren()[i] = null;
                System.out.print(temp.getName().substring(findDepth(temp.getName()))+ " is cut. cursor is at ");
            }
        }
        //shift
        for(int i = 0; i < temp.getParent().getChildren().length; i++){
            if(i<7){
                NetworkNode temp1 = temp.getParent().getChildren()[i];
                temp.getParent().getChildren()[i] = temp.getParent().getChildren()[i+1];
                temp.getParent().getChildren()[i+1] = temp1;
            }
        }
        cursor = temp.getParent();
        System.out.println(cursor.getName().substring(findDepth(cursor.getName())));
        return temp2;
    };
    /**
     * The function below adds a child node after the cursor
     */
    public void addChild(int index, NetworkNode node){
        for(int i = 0; i <= findDepth(cursor.getName()); i++){
            node.setName('1' + node.getName());
        }
        if(cursor.getChildren()[index-1] != null) {
            System.out.println("There is a node there");
        }else if(index-1 > 0 && cursor.getChildren()[index-2] == null){
            System.out.println("By placing here, you make a hole in the array");
        }else{
            cursor.getChildren()[index-1] = node;
            node.setParent(cursor);
            cursor = cursor.getChildren()[index-1];
        }
    }
    public void pasteChild(int index, NetworkNode node){
        node.setName(node.getName().substring(findDepth(node.getName())));
        for(int i = 1; i <= findDepth(cursor.getName()+1); i++){
            node.setName( 1 + node.getName());
        }
        if(cursor.getChildren()[index-1] != null) {
            System.out.println("There is a node there");
        }else if(index-1 > 0 && cursor.getChildren()[index-2] == null){
            System.out.println("By placing here, you make a hole in the array");
        }else{
            cursor.getChildren()[index-1] = node;
            System.out.println(node.getName().substring(findDepth(node.getName())) + " pasted as child of "
            + cursor.getName().substring(findDepth(cursor.getName())));
        }
    }
    /**
     * The function moves the cursor node to its child
     */
    public void cursorToChild(int index){
        if(cursor.getChildren()[index-1] != null){
            cursor = cursor.getChildren()[index-1];
            System.out.println("Cursor moved to " + cursor.getName().substring(findDepth(cursor.getName())));
        }
    };
    /**
     * The function moves the cursor node to its parents
     */
    public void cursorToParent(){
        cursor = cursor.getParent();
        System.out.println("Cursor moved to " + cursor.getName().substring(findDepth(cursor.getName())));
    };
    /**
     * The function below is a helper function, which finds the depth of a String
     */
    public static int findDepth(String str){
        int depth = 0;
        for(int i = 0; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i))){
                depth++;
            }
        }
        return depth;
    }

    /**
     * @return The create NodeArr creates a new NetworkNode
     */
    public NetworkNode[] createNodeArr(){
        return new NetworkNode[9];
    }

    /**
     * The isLeaf function determine where a node is a leaf in the tree
     * @param str
     * @return
     */
    public Boolean isLeaf(String str){
        for(int i = 0; i < str.length();i++){
            if(str.charAt(i) == '-'){
                return true;
            }
        }return false;
    }

    /**
     * The function below reads the file and builds a tree
     * @param filename
     * @return
     * @throws FileNotFoundException
     */
    public NetworkTree readFromFile(String filename) throws FileNotFoundException {
        URL path = NintendoNetwork.class.getResource(filename);
        File file = new File(path.getFile());
        Scanner sc = new Scanner(file);
        networkTree.root = null;
        while (sc.hasNext()){
            String str = sc.nextLine();
            NetworkNode newNode = new NetworkNode(str, isLeaf(str), false);
            if (findDepth(str) == 0) {
                networkTree.root = newNode;
                cursor = networkTree.root;
            }
            if (findDepth(str) >= 1){
                NetworkNode temp = networkTree.root;
                int position = str.charAt(findDepth(str)-1)-'0'-1;
                for(int i = 0; i < findDepth(str) - 1; i++){
                    temp = temp.getChildren()[(str.charAt(i) - '0')-1];
                }
                temp.getChildren()[position] = newNode;
                temp.getChildren()[position].setParent(temp);
            }
        }
        return networkTree;
    };

    /**
     * The function below saves the binary to the file;
     * @param tree
     * @param fileName
     */
    public void writeToFile(NetworkTree tree, String fileName) throws Exception {
        FileWriter myWriter = new FileWriter(fileName);
        List<NetworkNode> res = preOrder();
        for(int i = 0; i< res.size();i++){
            myWriter.write(res.get(i).getName() + "\n");
        }
        myWriter.close();
    };

    public List<NetworkNode> preOrder() {
        List<NetworkNode> res = new ArrayList<>();
        if(root == null){ return res; }
        Stack<NetworkNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            NetworkNode current = stack.peek();
            stack.pop();
            res.add(current);
            for(int i = 8; i>=0; i--){
                if(current.getChildren()[i] != null){
                    stack.push(current.getChildren()[i]);
                }
            }
        }
        return res;
    }
    public void cursorToMinimalBrokenSubtree(){
        List<NetworkNode> res = preOrder();
        for(int i = 0; i< res.size();i++){
            if(res.get(i).isBroken()){
                cursor = res.get(i);
                return;
            }
        }
    };
    /**
     * The function below determines the broken status of the device and sets it accordingly
     */
    public void cursorBrokenStatus(){
        if(cursor != null){
            if(cursor.isBroken()){
                cursor.setBroken(false);
            }else{
                cursor.setBroken(true);
            }
        }
    }


    /**
     * This function helps with indentation
     * @param str
     */
    public static void indentation(String str){
        for(int i = 0; i < findDepth(str); i++){
            System.out.print("  ");
        }
    }
    /**
     * The function below prints the NetworkTree functions.
     * @return
     */
    @Override
    public String toString() {
        List<String> list = new ArrayList<>();

        if(root == null){
            return list.toString();
        }

        Stack<NetworkNode> stack = new Stack<>();
        stack.push(networkTree.root);

        while(!stack.isEmpty()){
            NetworkNode current = stack.peek();
            stack.pop();

            list.add(current.getName());

            for(int i = 8; i>=0; i--){
                if(current.getChildren()[i] != null){
                    stack.push(current.getChildren()[i]);
                }
            }
        }
        for(int i = 0; i < list.size(); i++){
            String newStr = list.get(i);
            indentation(list.get(i));
            List<NetworkNode> res = preOrder();
            if(list.get(i) == cursor.getName()){
                System.out.print("->" + newStr.substring(findDepth(newStr)));
            }else if(!isLeaf(list.get(i))) {
                System.out.print(" +" + newStr.substring(findDepth(newStr)));
            }else{
                System.out.print(" " + newStr.substring(findDepth(newStr)));
            }
            for(int j = 0; j< res.size();j++) {
                if (list.get(i) == res.get(j).getName() && res.get(j).isBroken()) {
                    System.out.print(" ~Fault~");
                }
            }
            System.out.println();
        }

        return"";
    }
}
