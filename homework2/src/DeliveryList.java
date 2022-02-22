//Jimmy Zhang ID: 112844431 CSE 214 R02

/**
 * This class represents a List of Deliveries.
 * It has a head, tail, and cursor all made of DeliveryListNodes.
 * @author Jimmy Zhang
 */
public class DeliveryList {
    private DeliveryListNode head;
    private DeliveryListNode tail;
    private DeliveryListNode cursor;
    private int totalDeliveries = 0;

    /**
     * This constructor is an empty constructor that initializes the values of head tail and cursor.
     */
    public DeliveryList(){
        this.head = null;
        this.tail = null;
        this.cursor = null;
    };

    public DeliveryListNode getHead() { return head; }
    public void setHead(DeliveryListNode head) { this.head = head; }
    public DeliveryListNode getTail() { return tail; }
    public void setTail(DeliveryListNode tail) { this.tail = tail; }
    public DeliveryListNode getCursors() { return cursor; }
    public void setCursor(DeliveryListNode cursor) { this.cursor = cursor; }


    /**
     * This method returns the totol number of deliveries in the doubly linked list.
     * @return
     */
    public int numDeliveries(){
        return totalDeliveries;
    }

    /**
     * This method gets the value of the DeliveryListNode cursor and returns the data as a Delivery type.
     * @return
     */
    public Delivery getCursor(){
        if(cursor == null){
            return null;
        }
        return cursor.getData();
    }

    /**
     * This method resets the cursor to the head of the linkedlist
     */
    public void resetCursorToHead(){
        if(head != null){
            cursor = head;
            System.out.println("Cursor is at Head.");
        }else{
            System.out.println("List is Empty!");
        }
    }
    /**
     * This method resets the cursor to the tailed of the linked list
     */
    public void resetCursorToTail(){
        if(head != null){
            setCursor(tail);
            System.out.println("Cursor is at Tail.");
        }else{
            System.out.println("List is Empty!");
        }
    }

    /**
     * This method moves the cursor forward in the linked list
     * @throws EndOfListException
     */
    public void cursorForward() throws EndOfListException {
        if(cursor == tail){
            throw new EndOfListException("The list is at the tail");
        }else{
            cursor = cursor.getNext();
            System.out.println("Cursor has moved forward");
        }
    }

    /**
     * This method moves the cursor backward in the linked list
     * @throws EndOfListException
     */
    public void cursorBackward() throws EndOfListException{
        if(cursor == head){
            throw new EndOfListException("The list is at the head");
        }else{
            cursor = cursor.getPrev();
            System.out.println("Cursor has moved Backward");
        }
    }

    /**
     * This method takes a parameter of type Delivery and inserts it after the cursor.
     * @param newDelivery
     */
    public void insertAfterCursor(Delivery newDelivery){
        if(newDelivery == null){
            throw new IllegalArgumentException("This is an illegal argument");
        }

        DeliveryListNode newNode = new DeliveryListNode(newDelivery);
        DeliveryListNode temp = cursor;

        //no cursor
        if(head == null){
            setHead(newNode);
            setTail(newNode);
            setCursor(newNode);
            newNode.setNext(null);
            newNode.setPrev(null);
//            head.setNext(null);
//            head.setPrev(null);
//            tail.setNext(null);
//            tail.setPrev(null);
        }
        //cursor in middle and tail
        else if(temp != null && temp.getNext() != null){
            newNode.setNext(cursor.getNext());
            cursor.setNext(newNode);
            newNode.setPrev(temp);
            newNode.getNext().setPrev(newNode);
        }
        //cursor at tail
        else{
            System.out.println("I'm at tail");
            tail.setNext(newNode);
            newNode.setPrev(tail);
//            newNode.setNext(null);
//            tail.getNext().setPrev(null);
            tail = newNode;
            tail.setNext(null);
        }
    }

    /**
     * This methods adds a new cursor to the tail
     * @param newDelivery
     */
    public void appendToTail(Delivery newDelivery){
        if(newDelivery == null){
            throw new IllegalArgumentException("This is an illegal argument");
        }
        DeliveryListNode newNode = new DeliveryListNode(newDelivery);
        if(tail != null){
            newNode.setPrev(tail);
            tail.setNext(newNode);
            newNode.setNext(null);
            tail.getNext().setPrev(null);
            tail = newNode;
        }
    }

    /**
     * This method removes the cursor and the Delivery as well
     * @return
     * @throws EndOfListException
     */
    public Delivery removeCursor() throws EndOfListException {
        try {
            DeliveryListNode temp = cursor;
            Delivery delivery = cursor.getData();
            // If cursor at beginning
            if (cursor == head && cursor.getNext() != null) {
                head = head.getNext();
                cursor = head;
            // If cursor at end
            } else if (cursor == tail) {
                cursorBackward();
                temp.getPrev().setNext(null);
                temp.setPrev(null);
                // If cursor in middle
            } else {
                cursor.getPrev().setNext(temp.getNext());
                cursor.getNext().setPrev(temp.getPrev());
                cursorForward();
            }
            return delivery;
        }catch (NullPointerException e){
            throw new NullPointerException("Null Pointer, try again.");
        }
    }
    @Override
    public String toString() {
        return "DeliveryList{" +
                "\n   head = " + head +
                "\n   tail = " + tail +
                "\n   cursor = " + cursor +
                "\n   totalDeliveries = " + totalDeliveries +
                '}';
    }

    /**
     * This class creates and EndofListException and throws it as well.
     */
    public static class EndOfListException extends Exception{
        public EndOfListException(String errorMessage){
            super(errorMessage);
        }
    }
}
