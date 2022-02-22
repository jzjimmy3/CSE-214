//Jimmy Zhang ID: 112844431 CSE 214 R02

import java.util.Scanner;

/**
 * This class is created to run all the methods from Delivery, DeliveryListNodes, and DeliveryList Class
 * @author Jimmy Zhang
 */
public class DeliveryDriver {

    public static boolean quitValue = true;
    public static Scanner input = new Scanner(System.in);
    public static String menuValue;
    public static Delivery xCopy;
    public static String deliveryListValue;
    public static DeliveryList deliveryListA = new DeliveryList();
    public static DeliveryList deliveryListB = new DeliveryList();
    public static DeliveryList list = deliveryListA;


    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        mainMenu();
        while(quitValue){
            chooseMainMenu();
            System.out.println();
        }
    }

    /**
     * This methods displays the menu options for Delivery
     */
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

    /**
     * This class allows the user to choose a specific option in the menu
     * @throws Exception
     */
    public static void chooseMainMenu() throws Exception {
        try{
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
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This methods adds the Delivery to the doubly linked list
     */
    private static void addDelivery() {
        try{
            System.out.print("Please enter a source: ");
            input.nextLine();
            String source = input.nextLine();

            System.out.print("Please enter a destination: ");
            String destination = input.nextLine();

            System.out.print("Please enter any special instructions: ");
            String instructions = input.nextLine();
            Delivery newDelivery = new Delivery(source, destination, instructions);
            list.insertAfterCursor(newDelivery);

        }catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This methods removes the Delivery in the doubly linked list
     */
    private static void removeDelivery() throws Exception {
        try{
            list.removeCursor();
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }catch (DeliveryList.EndOfListException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This methods cuts the Delivery in the doubly linked list and stores the value of the Node that was removed
     */
    private static void cutCursor() throws DeliveryList.EndOfListException {
        try{
            xCopy = list.removeCursor();
            System.out.println("Cursor is cut.");
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }catch (DeliveryList.EndOfListException e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This methods pastes the value of Delivery in the doubly linked list
     */
    private static void pasteCursor() {
        try{
            list.insertAfterCursor(xCopy);
        }catch(NullPointerException e){
            throw new NullPointerException("Null Pointer");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method moves the cursor to the head of the list.
     */
    private static void cursorToHead() {
        try{
            list.resetCursorToHead();
        }catch(NullPointerException e){
            throw new NullPointerException("Null Pointer");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method moves the cursor to the tail of the list.
     */
    private static void cursorToTail() {
        try{
        list.resetCursorToTail();
        }catch(NullPointerException e){
            throw new NullPointerException("Null Pointer");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method moves the cursor one node forward
     */
    private static void cursorForward() throws DeliveryList.EndOfListException {
        try{
            list.cursorForward();
        }catch(NullPointerException e){
            throw new NullPointerException("Null Pointer");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method moves the cursor one node backward
     */
    private static void cursorBackward() throws DeliveryList.EndOfListException {
        try{
        list.cursorBackward();
        }catch(NullPointerException e){
            throw new NullPointerException("Null Pointer");
        }catch(DeliveryList.EndOfListException e){
            throw new NullPointerException("End of List Exception");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method switches the DeliveryList
     */
    private static void switchDeliveryList() {
        try{
            list = (list == deliveryListA) ? deliveryListB : deliveryListA;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method prints the DeliveryList
     */
    private static void printCurrentList(){
        try{
            DeliveryListNode temp = list.getHead();
            if(list.getHead() == null) {
                System.out.println("Empty Delivery List");
                return;
            }else{
                System.out.println("Delivery List: " + getDeliveryListValue());
                System.out.println("---------------------------------------------------------");

                while( temp != null) {
                    if(list.getCursors() == temp){
                        System.out.println("->");
                    }else{
                        System.out.println('~');
                    }
                    System.out.print(temp.getData() + "\n");
                    temp = temp.getNext();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method exits the program
     */
    public static void setQuitValue(){
        try{
            quitValue = false;
            System.out.println("Next time, try UPS!");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method stores the value of delivery list into a string format
     */
    public static String getDeliveryListValue() {
        if (list == deliveryListA) {
            deliveryListValue = "Biz Billy's Deliveries: ";
        } else {
            deliveryListValue = "Money Mike's Deliveries: ";
        }
        return deliveryListValue;
    }
}