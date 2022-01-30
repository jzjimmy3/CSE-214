//Jimmy Zhang ID: 112844431 CSE 214 R02

import java.util.Scanner;

public class RipoffRental {
    private static String menuOptionsValue, bookShelfValue;
    private static Bookshelf bookShelfNum;
    private static Bookshelf shelfA = new Bookshelf();
    private static Bookshelf shelfB;
    private static Bookshelf shelfC;

    public static void main(String[] args) {
        bookShelfNum = shelfValue();
        menuOptions();
//        Book myBook1 = new Book("Harry Potter1", "JK Rowling1", 1);
//        shelfA.addBook(0,myBook1);
        System.out.println(shelfA.toString());

    }

    private static void bookshelfA() {
    }

    public static void menuOptions(){
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
        System.out.println("Welcome to Jack's aMAzin' Textbook Rentals, highest price guaranteed!");
        System.out.print("Please enter a book shelf for viewing[A,B,C]: ");
        Scanner input = new Scanner(System.in);
        bookShelfValue = input.next();
        switch (bookShelfValue){
            case "A": return shelfA;
            case "B": return shelfB;
            case "C": return shelfC;
            default: break;
        }
        return null;
    }


    public static void addBookInstructions(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a title:");
        String titleInput = input.next();
        System.out.println("Please enter a author:");
        String authorInput= input.next();
        System.out.println("Please enter a condition:");
        int conditionInput = input.nextInt();
        System.out.println("Please enter a position on shelf:");
        int positionInput = input.nextInt();
        Book book = new Book(titleInput,authorInput,conditionInput);
        bookShelfNum.addBook(positionInput,book);
    }
}
