//Jimmy Zhang ID: 112844431 CSE 214 R02

/**
 * This class represents the Node that the Delivery Object would be stored in.
 * It has a data, next, and prev associated with it to represent a doubly linked list.
 * @author Jimmy Zhang
 */
public class DeliveryListNode {
    private Delivery data;
    private DeliveryListNode next;
    private DeliveryListNode prev;

    public DeliveryListNode(){};

    /**
     * This is a constructor used to create a new DeliveryListNode Object
     * @param initData
     * The initial data of the DeliveryListNode.
     */
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

    /**
     * @return
     */
    @Override
    public String toString() {
        DeliveryList deliveryList = new DeliveryList();
        DeliveryListNode current = deliveryList.getCursors().getNext();
        if (current == null) {
            return "[]";
        }
        else {
            String result = "[" + current.data;
            while (current.next != null)
            {
                result += current.data + ", ";
                current = current.next;
            }
            result += "]";
            return result;
       }
    }
}
