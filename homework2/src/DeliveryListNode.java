// Jimmy zhang ID: 112844431

public class DeliveryListNode {
    private Delivery data;
    private DeliveryListNode next;
    private DeliveryListNode prev;

    public DeliveryListNode(){};

    public DeliveryListNode(Delivery initData) {
        this.data = initData;
        this.next = null;
        this.prev = null;
    }

    public Delivery getData() { return data; }
    public void setData(Delivery data) { this.data = data; }
    public DeliveryListNode getNext() { return next; }
    public void setNext(DeliveryListNode next) { this.next = next; }
    public DeliveryListNode getPrev() { return prev; }
    public void setPrev(DeliveryListNode prev) { this.prev = prev; }

    @Override
    public String toString() {
        return "DeliveryListNode{" +
                "\n data=" + data +
                "\n next=" + next +
                "\n prev=" + prev +
                '}';
    }
}
