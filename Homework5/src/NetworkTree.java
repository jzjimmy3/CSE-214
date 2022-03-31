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
    public NetworkNode[] networkNodeDepth2 = new NetworkNode[9];

    public void cursorToRoot(){}
//    public NetworkNode cutCursor(){};
    public void addChild(int index, NetworkNode node){}
    public void cursorToChild(int index){};
    public void cursorToParent(){};



    public NetworkTree readFromFile(String filename) throws FileNotFoundException {
        URL path = NintendoNetwork.class.getResource("sbutopology.txt");
        File file = new File(path.getFile());
        Scanner sc = new Scanner(file);
        int depth = 0;
        while (sc.hasNext()){
            String string = sc.nextLine();
            int i = 0;
            depth = 0;
            if(Character.isLetter(string.charAt(i))){
                networkTree.root = new NetworkNode(string);
            }

            while(Character.isDigit(string.charAt(i))){
                depth++;
                i++;
            }
            i = 0;
            while(Character.isDigit(string.charAt(i))){
                int integer = string.charAt(i)-'0';
                if(depth == 1){
                    networkNodeDepth1[integer-1] = new NetworkNode(string);
                    networkTree.root.setChildren(networkNodeDepth1);
                }

                //11Mendy
                //21Tabler
                //31West
                //32GLS
                //

                //when i = 0
                //Integer  1 2 3 3
                //location C H W B
                //depth =  2 2 2 2
                //[]       0 1 2 2

                //when i == 1
                //Integer  1 1 1 2
                //Integer  1 2 3 3
                //[]       0 0 0 1

                if(depth == 2){
                    networkNodeDepth2[integer-1] = new NetworkNode(string);
                    networkTree.root.getChildren()[integer-1].setChildren(networkNodeDepth2);
                }

                i++;
            }

            //if no number, the string is a root, and the cursor should be there
            //and is the first element in node
//                System.out.print(string.charAt(i));\
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
