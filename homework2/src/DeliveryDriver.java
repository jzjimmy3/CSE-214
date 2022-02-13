// In book shelf, we ust Book[] array, but what about doubly linked lists? Is there something similar?
// Why am I getting error in this code?
// The DeliveryList class is made up of lists of Delivery Nodes, but how do I connect them? In bookshelf, they were connected through Books[].
// How can I print doubly linked list?
//What is the order I should add or remove a linked list?

import java.util.Scanner;

public class DeliveryDriver {

    public static boolean quitValue = true;
    public static Scanner input = new Scanner(System.in);
    public static String menuValue;

    public static DeliveryList deliveryListA = new DeliveryList();


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
        deliveryListA.insertAfterCursor(newDelivery);
    }

    private static void removeDelivery() {
        //removes Delivery, doesn't save anything

    }

    private static void cutCursor() throws DeliveryList.EndOfListException {
        //removes Delivery, stores object for future pasting
        //cursor moves forward
        deliveryListA.removeCursor();
        deliveryListA.cursorForward();
        System.out.println("Cursor is cut.");
    }

    private static void pasteCursor() {
    }

    private static void cursorToHead() {
        deliveryListA.resetCursorToHead();
    }

    private static void cursorToTail() {
        if(deliveryListA.getHead() != null){
            deliveryListA.setCursor(deliveryListA.getTail());
            System.out.println("Cursor is at Tail.");
        }
    }

    private static void cursorForward() throws DeliveryList.EndOfListException {
        deliveryListA.cursorForward();
    }

    private static void cursorBackward() throws DeliveryList.EndOfListException {
        deliveryListA.cursorBackward();
    }

    private static void switchDeliveryList() {
    }

    private static void printCurrentList(){
        DeliveryListNode temp = deliveryListA.getHead();
        if(deliveryListA.getHead() == null) {
            System.out.println("Empty Delivery List");
            return;
        }else{
            System.out.println("Delivery List: ");
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
}