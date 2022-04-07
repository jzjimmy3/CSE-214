import java.util.Scanner;

public class StormStatServer {
    public static Scanner input = new Scanner(System.in);
    public static boolean quitValue = false;


    public static void main(String[] args) {
        System.out.println("\nWelcome to the StormStatServer, we may not be able to make it rain, but we sure can tell you when it happened!\n");
        System.out.println("\nNo previous data found\n");
        displayMenu();
        while(!quitValue){
            menuOptions();
        }
    }
    public static void displayMenu(){
        System.out.println("" +
                "    A) Add A Storm\n" +
                "    L) Look Up A Storm\n" +
                "    D) Delete A Storm\n" +
                "    E) Edit Storm Data\n" +
                "    R) Print Storms Sorted By Rainfall\n" +
                "    W) W-Print Storms by Windspeed\n" +
                "    X) Save and quit\n" +
                "    Q) Quit and delete saved data ");
    }

    public static void menuOptions(){
        System.out.print("\nPlease select an option: ");
        switch (input.next().toUpperCase()){
            case "A": addStorm();
                break;
            case "L": lookStorm();
                break;
            case "D": removeStorm();
                break;
            case "E": editStorm();
                break;
            case "R": printByRain();
                break;
            case "W": printByWind();
                break;
            case "X": saveAndQuit();
                break;
            case "Q": Quit();
                break;
            default:
                System.out.println("Incorrect Menu Option. Try Again");
                break;
        }
    }

    private static void addStorm() {
    }

    private static void lookStorm() {
    }

    private static void removeStorm() {
    }

    private static void editStorm() {
    }

    private static void printByRain() {
    }

    private static void printByWind() {
    }

    private static void saveAndQuit() {
    }

    private static void Quit() {
        System.out.println("Goodbye, it's hard to hold an (electric) candle in the cold November (and April!) rain!.");
        quitValue = true;
    }
}
