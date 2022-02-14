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
    public DeliveryListNode getCursors() { return cursor; }
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
    public void resetCursorToTail(){
        if(head != null){
            setCursor(tail);
            System.out.println("Cursor is at Tail.");
        }else{
            System.out.println("List is Empty!");
        }
    }

    public void cursorForward() throws EndOfListException {
        if(cursor == tail){
            throw new EndOfListException("The list is at the tail");
        }else{
            cursor = cursor.getNext();
            System.out.println("Cursor has moved forward");
        }
    }

    public void cursorBackward() throws EndOfListException{
        if(cursor == head){
            throw new EndOfListException("The list is at the head");
        }else{
            cursor = cursor.getPrev();
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
//
//            newNode.setNext(null);
//            tail.getNext().setPrev(null);
            tail = newNode;
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

    public Delivery removeCursor() throws EndOfListException {
        try {
            DeliveryListNode temp = cursor;
            Delivery delivery = cursor.getData();
            // If cursor at beginning
            if (cursor == head && cursor.getNext() != null) {
                head = head.getNext();
                cursor = head;
                System.out.println("Hello");
            // If cursor at end
            } else if (cursor == tail) {
                cursorBackward();
                temp.getPrev().setNext(null);
                temp.setPrev(null);
                System.out.println("Hello1");
                // If cursor in middle
            } else {
                System.out.println("Hello2");
                cursor.getPrev().setNext(temp.getNext());
                cursor.getNext().setPrev(temp.getPrev());
                cursorForward();
            }
            return delivery;
        }catch (NullPointerException e){
            throw new NullPointerException("Null Pointer, try again.");
        }


//        try {
//            Delivery delivery = cursor.getData();
//            if (cursor == head && cursor.getNext() != null) {
//                head = head.getNext();
//                cursor = head;
//            } else if (cursor == head && cursor.getNext() == null) {
//                head = null;
//                cursor = null;
//                tail = null;
//            } else if (cursor.getPrev() != null && cursor.getNext() != null) {
//                DeliveryListNode temp = cursor;
//                //remove cursor
//                temp.getPrev().setNext(temp.getNext());
//                temp.getNext().setPrev(temp.getPrev());
//                if (cursor != tail) {
//                    cursorForward();
//                } else {
//                    cursorBackward();
//                }
//                System.out.println("Hello");
//                return delivery;
//            } else {
//                System.out.println("Hello1");
//
//                return null;
//            }
//            return delivery;
//        }catch (NullPointerException e){
//            throw new NullPointerException("Null Pointer, try again.");
//        }
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
