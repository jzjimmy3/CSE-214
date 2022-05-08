import java.util.Scanner;

public class HeapDriver {
    public static Scanner input = new Scanner(System.in);
    public static Boolean quitValue = false;
    public static Heaps newHeap = new Heaps(0);
    public static void main(String[] args) {
        mainMenu();
        newHeap.insertData(39);
        newHeap.insertData(72);
        newHeap.insertData(16);
        newHeap.insertData(24);
        newHeap.insertData(48);
        newHeap.insertData(95);
        newHeap.insertData(61);
        newHeap.insertData(83);
        newHeap.insertData(53);


        while(!quitValue){
            selectMenu();
        }
    }
    public static void mainMenu(){
        System.out.println("Welcome to the main menu: " +
                "\nI) Insert Element to Heap" +
                "\nR) Remove Element from Heap" +
                "\nP) Print Element from Heap" +
                "\nQ) Quit");
    }
    public static void selectMenu(){
        System.out.print("\nPlease select an option: ");
        switch (input.next().toUpperCase()){
            case "I" : insert();
                break;
            case "R" : remove();
                break;
            case "P" : print();
                break;
            case "Q" : quit();
                break;
            default:
                System.out.println("Sorry, Try again");
        }
    }

    private static void insert() {
        System.out.print("What number would you like to insert: ");
        newHeap.insertData(input.nextInt());
    }

    private static void remove() {
        newHeap.removeData();
    }
    private static void print() {
        newHeap.printHeap();
    }

    private static void quit() {
        quitValue = true;
    }
}
