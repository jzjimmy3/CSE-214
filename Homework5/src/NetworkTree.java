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
    public void cursorToRoot(){}
//    public NetworkNode cutCursor(){};
    public void addChild(int index, NetworkNode node){}
    public void cursorToChild(int index){};
    public void cursorToParent(){};



    public NetworkTree readFromFile(String filename) throws FileNotFoundException {
        URL path = NintendoNetwork.class.getResource("sbutopology.txt");
        File file = new File(path.getFile());
        Scanner sc = new Scanner(file);
        NetworkNode[] networkNodeArray = new NetworkNode[9];
        int depth = 0;
        while (sc.hasNext()){
            int lineNum = 1;
            String string = sc.nextLine();
            int i = 0;
            depth = 0;
            while(Character.isDigit(string.charAt(i))){
                if(depth == 0 ){
                    networkTree.root = new NetworkNode(string);
                }
                if(depth == 1 && string.charAt(i) == 1){
                    networkNodeArray[0] = new NetworkNode(string);
                    networkTree.root.setChildren(networkNodeArray);
                }
                depth++;
                i++;
            }
            System.out.println(depth);

            //if no number, the string is a root, and the cursor should be there
            //and is the first element in node
//                System.out.print(string.charAt(i));\
            lineNum++;
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
