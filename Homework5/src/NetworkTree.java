//Jimmy Zhang CSE 214 R02 ID: 112844431

import java.io.File;
import java.io.FileNotFoundException;
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
        }
    }
//    public NetworkNode cutCursor(){};
    /**
     * The function below adds a child node after the cursor
     */
    public void addChild(int index, NetworkNode node){
        if(cursor.getChildren()[index] != null) {
            System.out.println("There is a node there");
        }else if(cursor.getChildren()[index-1] == null){
            System.out.println("By placing here, you make a hole in the array");
        }else{
            cursor.getChildren()[index] = node;
        }
    }
    /**
     * The function moves the cursor node to its child
     */
    public void cursorToChild(int index){
        if(cursor.getChildren()[index] != null){
            cursor = cursor.getChildren()[index];
        }
    };
    /**
     * The function moves the cursor node to its parents
     */
    public void cursorToParent(){
        cursor = cursor.getParent();
    };
    /**
     * The function below is a helper function, which finds the depth of a String
     */
    public int findDepth(String str){
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
        URL path = NintendoNetwork.class.getResource("sbutopology.txt");
        File file = new File(path.getFile());
        Scanner sc = new Scanner(file);
        networkTree.root = null;
        while (sc.hasNext()){
            String str = sc.nextLine();
            if (findDepth(str) == 0) {
                networkTree.root = new NetworkNode(str.substring(findDepth(str)), isLeaf(str));
                cursor = networkTree.root;
            }
            if (findDepth(str) == 1){
                NetworkNode temp = networkTree.root;
                int integer0 = str.charAt(0)- '0';
                networkNodeDepth1[integer0-1] = new NetworkNode(str.substring(findDepth(str)),isLeaf(str));
                networkTree.root.setChildren(networkNodeDepth1);
                networkTree.root.getChildren()[integer0-1].setParent(networkTree.root);
            }
            if(findDepth(str) == 2){
                int integer0 = str.charAt(0)- '0';
                int integer1 = str.charAt(1) -'0';
                if(networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1] == null){
                    networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1] = new NetworkNode(str.substring(findDepth(str)),isLeaf(str));
                    networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1].setParent(networkTree.root.getChildren()[integer0-1]);
                }else{
                    NetworkNode[] nodeArr = createNodeArr();
                    nodeArr[integer1-1] = new NetworkNode(str.substring(findDepth(str)),isLeaf(str));
                    networkTree.root.getChildren()[integer0-1].setChildren(nodeArr);
                    networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1].setParent(networkTree.root.getChildren()[integer0-1]);
                }
            }
            if(findDepth(str) == 3){
                int integer0 = str.charAt(0) -'0';
                int integer1 = str.charAt(1) -'0';
                int integer2 = str.charAt(2) -'0';
                if(networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1].getChildren()[integer2-1] == null){
                    networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1].getChildren()[integer2-1] = new NetworkNode(str.substring(findDepth(str)),isLeaf(str));
                    networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1].getChildren()[integer2-1].setParent(networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1]);

                }else{
                    NetworkNode[] nodeArr = createNodeArr();
                    nodeArr[integer2-1] = new NetworkNode(str.substring(findDepth(str)), isLeaf(str));
                    networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1].setChildren(nodeArr);
                    networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1].getChildren()[integer2-1].setParent(networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1]);
                }
            }
        }
        return networkTree;
    };

    /**
     * The function below saves the binary to the file;
     * @param tree
     * @param filename
     */
    public static void writeToFile(NetworkTree tree, String filename){};
    public void cursorToMinimalBrokenSubtree(){};


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
            if(list.get(i) == cursor.getName()){
                System.out.println("->" + list.get(i) );
            }else if(!isLeaf(list.get(i))) {
                System.out.println("+" + list.get(i) );
            }else{
                System.out.println("    " + list.get(i));

            }
        }
        return"";

//        return "NetworkTree{" +
//                "root=" + networkTree.root +
//                '}';
    }
}
