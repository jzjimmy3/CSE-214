//public DeliveryListNode(Delivery initData)
//Default constructor.
//Preconditions:
//initData is not null.
//Postconditions:
//This DeliveryListNode has been initialized to wrap the indicated Delivery, and prev and next have been set to null.
//Throws:
//IllegalArgumentException if initData is null.


public class DeliveryListNode {
    private Delivery data;
    private DeliveryListNode next;
    private DeliveryListNode prev;

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
}
