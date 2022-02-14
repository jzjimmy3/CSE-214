// In book shelf, we ust Book[] array, but what about doubly linked lists? Is there something similar?
// Why am I getting error in this code?
// How can I print doubly linked list?
// What is the order I should add or remove a linked list?
// In switchDeliveryList() why can't I use the ternary operator?
// should I put everything in try catch?
//check the backwards function, why does cursor.getPrev( produce Method threw 'java.lang.NullPointerException' exception. Cannot evaluate DeliveryListNode.toString()

import java.util.Scanner;

public class DeliveryDriver {

    public static boolean quitValue = true;
    public static Scanner input = new Scanner(System.in);
    public static String menuValue;
    public static Delivery xCopy;
    public static String deliveryListValue;
    public static DeliveryList deliveryListA = new DeliveryList();
    public static DeliveryList deliveryListB = new DeliveryList();
    public static DeliveryList list = deliveryListA;


    public static void main(String[] args) throws Exception {
        mainMenu();
        while(quitValue){
            chooseMainMenu();
            System.out.println();
        }
    }

    public static void mainMenu(){
        System.out.println("Welcome to the Delinquent Dollar Delivery Scheduler.");
        System.out.println("Menu: \n" +
                "   A) Add a Delivery After Cursor\n" +
                "   R) Remove Delivery At Cursor\n" +
                "   X) Cut Cursor\n" +
                "   V) Paste After Cursor\n" +
                "   H) Cursor to Head\n" +
                "   T) Cursor to Tail\n" +
                "   F) Cursor Forward\n" +
                "   B) Cursor Backward\n" +
                "   S) Switch Delivery Lists\n" +
                "   P) Print Current List\n" +
                "   Q) Quit"
        );
    }

    public static void chooseMainMenu() throws Exception {
        System.out.print("Please select an option: ");
        menuValue = input.next().toUpperCase();
        switch(menuValue){
            case "A" : addDelivery();
                break;
            case "R": removeDelivery();
                break;
            case "X": cutCursor();
                break;
            case "V": pasteCursor();
                break;
            case "H": cursorToHead();
                break;
            case "T": cursorToTail();
                break;
            case "F": cursorForward();
                break;
            case "B": cursorBackward();
                break;
            case "S": switchDeliveryList();
                break;
            case "P": printCurrentList();
                break;
            case "Q": setQuitValue();
                break;
            default:break;
        }
    }

    private static void addDelivery() {
        System.out.print("Please enter a source: ");
        input.nextLine();
        String source = input.nextLine();

        System.out.print("Please enter a destination: ");
        String destination = input.nextLine();

        System.out.print("Please enter any special instructions: ");
        String instructions = input.nextLine();
        Delivery newDelivery = new Delivery(source, destination, instructions);
        list.insertAfterCursor(newDelivery);
    }

    private static void removeDelivery() throws Exception {
        try{
            list.removeCursor();
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }catch (DeliveryList.EndOfListException e){
            System.out.println(e.getMessage());
        }
    }

    private static void cutCursor() throws DeliveryList.EndOfListException {
        xCopy = list.removeCursor();
        System.out.println("Cursor is cut.");
    }

    private static void pasteCursor() {
        try{
            list.insertAfterCursor(xCopy);
        }catch(NullPointerException e){
            throw new NullPointerException("The value you are trying to paste is empty");
        }
    }

    private static void cursorToHead() {
        list.resetCursorToHead();
    }

    private static void cursorToTail() {
        list.resetCursorToTail();
    }

    private static void cursorForward() throws DeliveryList.EndOfListException {
        list.cursorForward();
    }

    private static void cursorBackward() throws DeliveryList.EndOfListException {
        list.cursorBackward();
    }

    private static void switchDeliveryList() {
        // (switchDelivery == deliveryListA) ? (switchDelivery = deliveryListB) : (switchDelivery = deliveryListA);

        if ((list == deliveryListA)) {
            list = deliveryListB;
        } else {
            list = deliveryListA;
        }
    }

    private static void printCurrentList(){
        DeliveryListNode temp = list.getHead();
        if(list.getHead() == null) {
            System.out.println("Empty Delivery List");
            return;
        }else{
            System.out.println("Delivery List: " + getDeliveryListValue());
            System.out.println("---------------------------------------------------------");

            while( temp != null) {
                System.out.print(temp.getData() + "\n");
                temp = temp.getNext();
                System.out.println();
            }
        }
    }

    public static void setQuitValue(){
        quitValue = false;
        System.out.println("Next time, try UPS!");
    }

    // new methods
    public static String getDeliveryListValue(){
        if(list == deliveryListA){
            deliveryListValue = "Biz Billy's Deliveries: ";
        }else{
            deliveryListValue = "Money Mike's Deliveries: ";
        }
        return deliveryListValue;
    }
}