import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class NetworkTree {
    public static NetworkTree networkTree = new NetworkTree();
    public static Scanner input = new Scanner(System.in);

    private NetworkNode root;
    private static NetworkNode cursor;
    public NetworkTree(){};
    public NetworkTree(NetworkNode root, NetworkNode cursor) {
        this.root = root;
        this.cursor = cursor;
    }
    public NetworkNode[] networkNodeDepth1 = new NetworkNode[9];
    public void cursorToRoot(){
        if(networkTree.root != null){
            cursor = networkTree.root;
        }
    }
//    public NetworkNode cutCursor(){};
    public void addChild(int index, NetworkNode node){
        if(cursor.getChildren()[index] != null) {
            System.out.println("There is a node there");
        }else if(cursor.getChildren()[index-1] == null){
            System.out.println("By placing here, you make a hole in the array");
        }else{
            cursor.getChildren()[index] = node;
        }
    }
    public void cursorToChild(int index){
        if(cursor.getChildren()[index] != null){
            cursor = cursor.getChildren()[index];
        }
    };
    public void cursorToParent(){
        cursor = cursor.getParent();
    };

    public int findDepth(String str){
        int depth = 0;
        for(int i = 0; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i))){
                depth++;
            }
        }
        return depth;
    }
    public NetworkNode[] createNodeArr(){
        return new NetworkNode[9];
    }
    public Boolean isLeaf(String str){
        for(int i = 0; i < str.length();i++){
            if(str.charAt(i) == '-'){
                return true;
            }
        }return false;
    }

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

    public static void writeToFile(NetworkTree tree, String filename){};
    public void cursorToMinimalBrokenSubtree(){};


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
