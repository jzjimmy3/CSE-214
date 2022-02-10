//Jimmy Zhang ID: 112844431

public class Delivery {
    private String source;
    private String dest;
    private String instruction;

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

    @Override
    public String toString() {
        return "Delivery{" +
                "source='" + source + '\'' +
                ", dest='" + dest + '\'' +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}
