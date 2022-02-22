//Jimmy Zhang ID: 112844431 CSE 214 R02

/**
 * This class represents the contents of Delivery.
 * It has a Source, Destination, and Instruction associated with it
 * @author Jimmy Zhang
 */
public class Delivery {
    private String source;
    private String dest;
    private String instruction;


    /**
     * This is a constructor used to create a new Delivery Object
     * @param source
     * The source of the delivery
     * @param dest
     * the destination of the delivery
     * @param instruction
     * The insturctions of the delivery
     */
    public Delivery(String source, String dest, String instruction) {
        this.source = source;
        this.dest = dest;
        this.instruction = instruction;
    }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getDest() { return dest; }
    public void setDest(String dest) { this.dest = dest; }
    public String getInstruction() { return instruction; }
    public void setInstruction(String instruction) { this.instruction = instruction; }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "To: " + source + " | From: " + dest + "\n" +
                "Instructions:" + instruction;
    }
}
