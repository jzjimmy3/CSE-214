import java.util.Arrays;

public class NetworkNode {
    private String name;
    private boolean isNintendo; // if true there is a - like " -BasementClub"
    private boolean isBroken = false;
    private NetworkNode parent;
    private NetworkNode[] children;
    final int maxChildren = 9;

    public NetworkNode(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "NetworkNode{" +
                "name='" + name + '\'' +
                ", isNintendo=" + isNintendo +
                ", isBroken=" + isBroken +
                ", parent=" + parent +
                ", children=" + Arrays.toString(children) +
                ", maxChildren=" + maxChildren +
                '}';
    }
}
