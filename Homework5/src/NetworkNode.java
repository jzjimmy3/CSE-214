//Jimmy Zhang CSE 214 R02 ID: 112844431
import java.util.Arrays;

/**
 * The class below represents the NetworkNode.
 * The main parameters of this class is: name, isNintendo, isBroken, parent, children
 * @author Jimmy Zhang
 */
public class NetworkNode {
    private String name;
    private boolean isNintendo; // if true there is a - like " -BasementClub"
    private boolean isBroken = false;
    private NetworkNode parent;
    private NetworkNode[] children;
    final int maxChildren = 9;

    /**
     * The function below represents the constructor function for NetworkNode
     * The name parameter is a string
     * The isNintendo parameter is a boolean
     * @param name
     * @param isNintendo
     */
    public NetworkNode(String name, boolean isNintendo, boolean isBroken) {
        this.name = name;
        this.isNintendo = isNintendo;
        this.isBroken = isBroken;
//        this.children = children;
        this.children = new NetworkNode[maxChildren];
//        this.parent = parent;
    }

    /**
     * The functions below are getter and setter methods.
     */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isNintendo() { return isNintendo; }
    public void setNintendo(boolean nintendo) { isNintendo = nintendo; }

    public boolean isBroken() { return isBroken; }
    public void setBroken(boolean broken) { isBroken = broken; }

    public NetworkNode getParent() { return parent; }
    public void setParent(NetworkNode parent) { this.parent = parent; }

    public NetworkNode[] getChildren() { return children; }
    public void setChildren(NetworkNode[] children) { this.children = children; }

    public int getMaxChildren() { return maxChildren; }


    /**
     * The toString methods prints the parameters of NetworkNode
     * @return
     */
    @Override
    public String toString() {
        return "\n NetworkNode{" +
                "name='" + name + '\'' +
                ", isNintendo=" + isNintendo +
                ", isBroken=" + isBroken +
//                ", parent=" + parent +
                ", \nchildren=" + Arrays.toString(children) +
                ", maxChildren=" + maxChildren +
                '}';
    }
}
