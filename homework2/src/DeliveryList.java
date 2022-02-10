public class DeliveryList {
    private DeliveryListNode head;
    private DeliveryListNode tail;
    private DeliveryListNode cursor;
    private int totalDeliveries = 0;

    public DeliveryList(){
        this.head = null;
        this.tail = null;
        this.cursor = null;
    };

    public DeliveryListNode getHead() { return head; }
    public void setHead(DeliveryListNode head) { this.head = head; }
    public DeliveryListNode getTail() { return tail; }
    public void setTail(DeliveryListNode tail) { this.tail = tail; }
    // public DeliveryListNode getCursor() { return cursor; }
    public void setCursor(DeliveryListNode cursor) { this.cursor = cursor; }

    public int numDeliveries(){
        return totalDeliveries;
    }

    public Delivery getCursor(){
        if(cursor == null){
            return null;
        }
        return cursor.getData();
    }

    public void resetCursorToHead(){
        if(head != null){
            cursor = head;
        }else{
            cursor = null;
        }
    }

    public void cursorForward() throws EndOfListException {
        DeliveryListNode temp = cursor;
        if(temp.getNext() != null){
            if(temp == tail){
                throw new EndOfListException("The list is at the tail");
            }
            temp.setNext(temp.getNext());
        }
    }

    public void cursorBackward() throws EndOfListException{
        DeliveryListNode temp = cursor;
        if(temp.getPrev() != null){
            if(temp == head){
                throw new EndOfListException("The list is at the head");
            }
            temp.setPrev(temp.getPrev());
        }
    }

    public void insertAfterCursor(Delivery newDelivery){
        if(newDelivery == null){
            throw new IllegalArgumentException("This is an illegal argument");
        }
        DeliveryListNode newNode = new DeliveryListNode(newDelivery);
        DeliveryListNode temp = cursor;

        if(cursor == null){
            System.out.println("hello");
            setHead(newNode);
            setTail(newNode);
            setCursor(newNode);
            //newNode.setNext(new DeliveryListNode(new Delivery("zxcv","zxcv","zxcv")));
        }
        else if(cursor == tail){
            System.out.println("hello1");
            newNode.setNext(null);
            temp.setNext(newNode);
            newNode.setPrev(cursor);
        }
        else{
            System.out.println("hello2");
            newNode.setNext(cursor.getNext());
            temp.setNext(newNode);
            newNode.setPrev(cursor);
            newNode.getNext().setNext(newNode);
        }
    }

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

    public Delivery removeCursor(){
        if(cursor != null && cursor.getNext()!= null){
            Delivery delivery = cursor.getData();
            cursor.setNext(cursor.getNext());
            return delivery;
        }
         return null;
    }

    @Override
    public String toString() {
        return "DeliveryList{" +
                "\n head=" + head +
                "\n tail=" + tail +
                "\n cursor=" + cursor +
                "\n totalDeliveries=" + totalDeliveries +
                '}';
    }

    public static class EndOfListException extends Exception{
        public EndOfListException(String errorMessage){
            super(errorMessage);
        }
    }
}
