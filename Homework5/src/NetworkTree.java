import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class NetworkTree {
    public static NetworkTree networkTree = new NetworkTree();
    public static Scanner input = new Scanner(System.in);

    private NetworkNode root;
    private NetworkNode cursor;
    public NetworkTree(){};
    public NetworkTree(NetworkNode root, NetworkNode cursor) {
        this.root = root;
        this.cursor = cursor;
    }
    public NetworkNode[] networkNodeDepth1 = new NetworkNode[9];
    public void cursorToRoot(){}
//    public NetworkNode cutCursor(){};
    public void addChild(int index, NetworkNode node){}
    public void cursorToChild(int index){};
    public void cursorToParent(){};

    public void preOrder() {
        System.out.println(cursor);;
    }
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

    public NetworkTree readFromFile(String filename) throws FileNotFoundException {
        URL path = NintendoNetwork.class.getResource("sbutopology.txt");
        File file = new File(path.getFile());
        Scanner sc = new Scanner(file);
        networkTree.root = null;
        while (sc.hasNext()){
            String str = sc.nextLine();
            if (findDepth(str) == 0) networkTree.root = new NetworkNode(str);
            if (findDepth(str) == 1){
                int integer = str.charAt(0)- '0';
                networkNodeDepth1[integer-1] = new NetworkNode(str);
                networkTree.root.setChildren(networkNodeDepth1);
            }
            if(findDepth(str) == 2){
                int integer0 = str.charAt(0)- '0';
                int integer1 = str.charAt(1) -'0';
                if(networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1] == null){
                    networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1] = new NetworkNode(str);
                }else{
                    NetworkNode[] nodeArr = createNodeArr();
                    nodeArr[integer1-1] = new NetworkNode(str);
                    networkTree.root.getChildren()[integer0-1].setChildren(nodeArr);
                }
            }
            if(findDepth(str) == 3){
                int integer0 = str.charAt(0) -'0';
                int integer1 = str.charAt(1) -'0';
                int integer2 = str.charAt(2) -'0';
                if(networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1].getChildren()[integer2-1] == null){
                    networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1].getChildren()[integer2-1] = new NetworkNode(str);
                }else{
                    NetworkNode[] nodeArr = createNodeArr();
                    nodeArr[integer2-1] = new NetworkNode(str);
                    networkTree.root.getChildren()[integer0-1].getChildren()[integer1-1].setChildren(nodeArr);
                }
            }
        }
        return networkTree;
    };

    public static void writeToFile(NetworkTree tree, String filename){};
    public void cursorToMinimalBrokenSubtree(){};

    @Override
    public String toString() {
        return "NetworkTree{" +
                "root=" + networkTree.root +
                ", cursor=" + cursor +
                '}';
    }
}
