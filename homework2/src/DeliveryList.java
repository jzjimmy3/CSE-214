public class DeliveryList {
    private DeliveryListNode head;
    private DeliveryListNode tail;
    private DeliveryListNode cursor;
    private int totalDeliveries;

    public DeliveryList(){
        this.head = null;
        this.tail = null;
        this.cursor = null;
    };

    public int numDeliveries(){ return totalDeliveries; }
    public DeliveryListNode getHead() { return head; }
    public void setHead(DeliveryListNode head) { this.head = head; }
    public DeliveryListNode getTail() { return tail; }
    public void setTail(DeliveryListNode tail) { this.tail = tail; }
    public DeliveryListNode getCursor() { return cursor; }
    public void setCursor(DeliveryListNode cursor) { this.cursor = cursor; }
}
