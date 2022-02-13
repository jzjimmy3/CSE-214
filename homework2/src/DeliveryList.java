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
//     public DeliveryListNode getCursor() { return cursor; }
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
            System.out.println("Cursor is at Head.");
        }else{
            System.out.println("List is Empty!");
        }
    }

    public void cursorForward() throws EndOfListException {
        if(cursor.getNext() != null){
//            DeliveryListNode temp = cursor;
            if(cursor == tail){
                throw new EndOfListException("The list is at the tail");
            }
            cursor = cursor.getNext(); // why can't I do cursor.setNext(cursor.getNext())
            System.out.println("Cursor has moved forward");
        }
    }

    public void cursorBackward() throws EndOfListException{
        if(cursor.getPrev() != null){
            // DeliveryListNode temp = cursor;
            if(cursor == head){
                throw new EndOfListException("The list is at the head");
            }
            cursor = cursor.getPrev(); // why can't I do cursor.setNext(cursor.getNext())
            System.out.println("Cursor has moved Backward");
        }
    }

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
            head.setPrev(null);
            tail.setNext(null);
        }
        //cursor in middle
        else if(temp != null && temp.getNext() != null){
            newNode.setNext(cursor.getNext());
            cursor.setNext(newNode);
            newNode.setPrev(temp);
            newNode.getNext().setPrev(newNode);
        }
        //cursor at tail
        else{
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }

//        if(cursor == null){
//            System.out.println("hello");
//            setHead(newNode);
//            setTail(newNode);
//            setCursor(newNode);
//            //newNode.setNext(new DeliveryListNode(new Delivery("zxcv","zxcv","zxcv")));
//        }
//        else if(cursor == tail){
//            System.out.println("hello1");
//            newNode.setNext(null);
//            temp.setNext(newNode);
//            newNode.setPrev(cursor);
//        }
//        else{
//            System.out.println("hello2");
//            newNode.setNext(cursor.getNext());
//            temp.setNext(newNode);
//            newNode.setPrev(cursor);
//            newNode.getNext().setNext(newNode);
//        }
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

    public Delivery removeCursor() throws EndOfListException {
        if(cursor == head){
            head = null;
            cursor = null;
            tail = null;
        }
        else if(cursor == head && cursor.getNext() != null){
            head = cursor.getNext();
        }
        else if(cursor.getPrev() != null && cursor.getNext()!= null){
            DeliveryListNode temp = cursor;
            Delivery delivery = cursor.getData();
            //remove cursor
            temp.getPrev().setNext(temp.getNext());
            temp.getNext().setPrev(temp.getPrev());
            if(cursor != tail){
                cursorForward();
            } else{
                cursorBackward();
            }

            return delivery;
        }else{
            return null;
        }
        return null;
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

    public static class EndOfListException extends Exception{
        public EndOfListException(String errorMessage){
            super(errorMessage);
        }
    }
}
