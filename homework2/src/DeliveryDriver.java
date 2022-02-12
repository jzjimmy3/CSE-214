// In book shelf, we ust Book[] array, but what about doubly linked lists? Is there something similar?
// Why am I getting error in this code?
// The DeliveryList class is made up of lists of Delivery Nodes, but how do I connect them? In bookshelf, they were connected through Books[].
// How can I print doubly linked list?

import java.util.Scanner;

public class DeliveryDriver {

    public static boolean quitValue = true;
    public static Scanner input = new Scanner(System.in);
    public static String menuValue;

    public static DeliveryList deliveryListA = new DeliveryList();


    public static void main(String[] args) {
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

    public static void chooseMainMenu(){
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
    }

    private static void cutCursor() {
    }

    private static void pasteCursor() {
    }

    private static void cursorToHead() {
    }

    private static void cursorToTail() {
    }

    private static void cursorForward() {
    }

    private static void cursorBackward() {
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
            while( temp != null) {
                System.out.print(temp.getData() + " -> ");
                temp = temp.getNext();
            }
        }
    }

    public static void setQuitValue(){
        quitValue = false;
        System.out.println("Next time, try UPS!");
    }
}