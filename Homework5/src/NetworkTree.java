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
    public int indexValue(String str,int index){
        return str.charAt(index)-'0';
    }
    public NetworkTree buildTree(String str, int depth, int position){
        if (depth == 0) networkTree.root = new NetworkNode(str);
        if(networkTree.root.getChildren()[position] == null){
            
        }
        return networkTree;
    }

    public NetworkTree readFromFile(String filename) throws FileNotFoundException {
        URL path = NintendoNetwork.class.getResource("sbutopology.txt");
        File file = new File(path.getFile());
        Scanner sc = new Scanner(file);
        networkTree.root = null;
        while (sc.hasNext()){
            String str = sc.nextLine();

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
