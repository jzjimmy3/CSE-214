//Jimmy Zhang ID: 112844431 CSE 214 R02

import java.util.Scanner;

public class RipoffRental {
    private static String menuOptionsValue, bookShelfValue;
    private static boolean  quitValue;
    private static Bookshelf bookShelfNum;
    private static Bookshelf shelfA = new Bookshelf();
    private static Bookshelf shelfB = new Bookshelf();
    private static Bookshelf shelfC = new Bookshelf();

    public static void main(String[] args) {
        menuOptions();
        while(quitValue != true)
            chooseMenuOptions();
    }

    public static void menuOptions(){
        System.out.println("Welcome to Jack's aMAzin' Textbook Rentals, highest price guaranteed!");
        bookShelfNum = shelfValue();
        System.out.println("What would you like to do?\n" +
                "   A) Add Book\n" +
                "   S) Swap Book\n" +
                "   L) Loan Book\n" +
                "   R) Remove Book\n" +
                "   D) Duplicate Book\n" +
                "   C) Change Shelf\n" +
                "   O) Overwrite shelf with clone of current shelf\n" +
                "   E) Check if two shelves are equal\n" +
                "   P) Print current bookshelf\n" +
                "   Q) Quit"
        );
    }


    public static void chooseMenuOptions(){
        System.out.print("Please select an option: ");

        Scanner input = new Scanner(System.in);
        menuOptionsValue = input.next();

        switch(menuOptionsValue.toUpperCase()){
            case "A": addBookInstructions();
                break;
            case "S": swapBookInstructions();
                break;
            case "L": loanBookInstructions();
                break;
            case "R": removeBookInstructions();
                break;
            case "D": duplicateBookInstructions();
                break;
            case "C": changeShelfInstructions();
                break;
            case "O": overwriteInstructions();
                break;
            case "E": equalInstructions();
                break;
            case "P": printInstructions();
                break;
            case "Q": quitInstructions();
                break;
            default:break;
        }

    }

    public static Bookshelf shelfValue(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a book shelf for viewing[A,B,C]: ");
        bookShelfValue = input.next();
        switch (bookShelfValue.toUpperCase()){
            case "A": return shelfA;
            case "B": return shelfB;
            case "C": return shelfC;
            default: break;
        }
        return null;
    }


    public static void addBookInstructions(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter a title: ");
        String titleInput = input.nextLine();
        System.out.print("Please enter a author: ");
        String authorInput= input.nextLine();
        System.out.print("Please enter a condition: ");
        int conditionInput = input.nextInt();
        System.out.print("Please enter a position on shelf (0 is first position): ");
        int positionInput = input.nextInt();

        Book book = new Book(titleInput,authorInput,conditionInput);
        bookShelfNum.addBook(positionInput,book);
    }

    private static void swapBookInstructions() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter an index: ");
        int index1Input = input.nextInt();
        System.out.println("Please enter another index: ");
        int index2Input = input.nextInt();
        bookShelfNum.swapBooks(index1Input,index2Input);
    }

    private static void loanBookInstructions() {
    }

    private static void removeBookInstructions() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter an index to remove book: ");
        int indexInput = input.nextInt();
        bookShelfNum.removeBook(indexInput);
    }
    private static void duplicateBookInstructions() {
    }

    private static void changeShelfInstructions() {
        bookShelfNum = shelfValue();
    }

    private static void overwriteInstructions() {
    }

    private static void equalInstructions() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please Select a shelf: ");
        String index1 = input.next().toUpperCase();
        System.out.print("Please Select another shelf: ");
        String index2 = input.next().toUpperCase();
        System.out.println(shelfA.equals(shelfB));
    }

    private static void printInstructions() {
        System.out.println("Bookshelf " + bookShelfValue + ":");
        System.out.println("Spot Title                                                Author              Condition          Borrower" );
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        for(int i = 0 ; i < bookShelfNum.CAPACITY; i++){
            if(bookShelfNum.getBook(i) != null){
                System.out.println(i + 1 + ". " +
                        bookShelfNum.getBook(i).getTitle() + "        " +
                        bookShelfNum.getBook(i).getAuthor() + "        " +
                        bookShelfNum.getBook(i).getCondition()+ "        "+
                        bookShelfNum.getBook(i).getBorrower()
                );
            }
        }
    }

    private static void quitInstructions() {
        quitValue = true;
        System.out.println("Goodbye!");
    }
}
