//Jimmy Zhang ID: 112844431 CSE 214 R02

import java.util.Scanner;

/**
 * This class is created to run all the methods created from Book and Bookshelf class
 * @author Jimmy Zhang
 */
public class RipoffRental {
    private static String menuOptionsValue, bookShelfValue;
    private static boolean  quitValue;
    private static Bookshelf shelfRow;
    private static Bookshelf shelfA = new Bookshelf();
    private static Bookshelf shelfB = new Bookshelf();
    private static Bookshelf shelfC = new Bookshelf();
    private static Scanner input = new Scanner(System.in);

    /**
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws Exception {
        menuOptions();
        while(quitValue != true){
            chooseMenuOptions();
            System.out.println();
        }
    }

    /**
     * This method displays the options available manipulating the bookshelf.
     */
    public static void menuOptions(){
        System.out.println("Welcome to Jack's aMAzin' Textbook Rentals, highest price guaranteed!");
        System.out.print("Please enter a book shelf for viewing[A,B,C]: ");
        shelfRow = shelfValue();
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

    /**
     * This method allows users to the chosen option menus to be implemented.
     * A switch statement is used to call its respective functions
     * @throws CloneNotSupportedException
     */
    public static void chooseMenuOptions() throws Exception {
        System.out.print("Please select an option: ");
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

    /**
     * This method determines the shelf we are currently looking at or trying to manipulate.
     * @return
     */
    public static Bookshelf shelfValue(){
        bookShelfValue = input.next();
        switch (bookShelfValue.toUpperCase()){
            case "A": return shelfA;
            case "B": return shelfB;
            case "C": return shelfC;
            default: break;
        }
        return null;
    }

    /**
     * This method displays the instructions necessary to add a book.
     * The Scanner object is used to request user input on title, author, condition, and shelf position.
     */
    public static void addBookInstructions(){
        try{
            input.nextLine();
            System.out.print("Please enter a title: ");
            String titleInput = input.nextLine();
            System.out.print("Please enter a author: ");
            String authorInput= input.nextLine();
            System.out.print("Please enter a condition(1-5): ");
            int conditionInput = input.nextInt();
            System.out.print("Please enter a position on shelf (0 is first position): ");
            int positionInput = input.nextInt();

            Book book = new Book(titleInput,authorInput,conditionInput);
            shelfRow.addBook(positionInput,book);
        }catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }
    /**
     * This method displays the instructions necessary to swap a book.
     * The Scanner object is used to request user input on index1 and index2.
     */
    private static void swapBookInstructions() {
        System.out.print("Please enter an index: ");
        int index1Input = input.nextInt();
        System.out.print("Please enter another index: ");
        int index2Input = input.nextInt();
        try{
            shelfRow.swapBooks(index1Input,index2Input);
            System.out.println(shelfRow.getBook(index1Input).getTitle() + " has swapped with " + shelfRow.getBook(index2Input).getTitle());
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Invalid Inputs, Try Again!");
        }
    }

    /**
     * This method displays the instructions necessary to loan a book.
     * The Scanner object is used to request user input on recipient, index, and condition.
     */
    private static void loanBookInstructions() throws Exception {
        try{
            input.nextLine();
            System.out.print("Please enter a recipient: ");
            String recipient = input.nextLine();
            System.out.print("Please enter an index: ");
            int bookIndex = input.nextInt();
            System.out.print("Please enter condition(1-5): ");
            int condition = input.nextInt();

            shelfRow.getBook(bookIndex).setBorrower(recipient);
            shelfRow.getBook(bookIndex).setCondition(condition);

            System.out.println(shelfRow.getBook(bookIndex).getTitle() + " has been loaned out to " + shelfRow.getBook(bookIndex).getBorrower());
        }catch(Exception e){
            System.out.println("Invalid Inputs, Try Again!");
        }
    }

    /**
     * This method displays the instructions necessary to remove a book.
     * The Scanner object is used to request user input on index.
     */
    private static void removeBookInstructions() throws Exception {
        System.out.print("Please enter an index to remove book: ");
        int indexInput = input.nextInt();
        try{
            shelfRow.removeBook(indexInput);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method displays the instructions necessary to duplicate a book.
     * The Scanner object is used to request user input on source index, and destination index.
     */
    private static void duplicateBookInstructions() throws Exception {
        System.out.print("Please enter a source index: ");
        int sourceIndex = input.nextInt();
        System.out.print("Please enter a destination index: ");
        int destinationIndex = input.nextInt();

        Book elem = shelfRow.getBook(sourceIndex);
        try{
            shelfRow.addBook(destinationIndex, new Book(elem.getTitle(), elem.getAuthor(),elem.getCondition()));
            System.out.println();
            System.out.println("A new copy of " + elem.getTitle() + " is in index " + destinationIndex + ".");
        }catch(Exception e){
            System.out.println("Catching Error");
        }
    }

    /**
     * This method displays the instructions necessary to change viewing shelf.
     * The method is dependent on shelfValue() method.
     */
    private static void changeShelfInstructions() {
        System.out.print("Please enter a book shelf for viewing[A,B,C]: ");
        shelfRow = shelfValue();
        System.out.println("Shelf " + bookShelfValue + " selected.");
    }

    /**
     * This method displays the instructions necessary to overwrite a shelf.
     * The method is dependent on Bookshelf's clone method
     */
    private static void overwriteInstructions() throws CloneNotSupportedException {
        System.out.print("Please select shelf to overwrite: ");
        Bookshelf bookshelfReplace = shelfRow.clone();
        String result = input.next();
        switch(result.toUpperCase()){
            case "A": shelfA = bookshelfReplace;
                break;
            case "B": shelfB = bookshelfReplace;
                break;
            case "C": shelfC = bookshelfReplace;
            default: break;
        }
        System.out.println();
        System.out.println("Shelf " + result.toUpperCase() + " overwritten with a copy of Shelf " + bookShelfValue);
    }

    /**
     * This method displays the instructions necessary to determine where two shelves are equal.
     * The method is dependent on Bookshelf's equals method
     */
    private static void equalInstructions() throws Bookshelf.EmptyShelfException {
        System.out.print("Please select a shelf: ");
        Bookshelf bookShelf1= shelfValue();
        System.out.print("Please select another shelf: ");
        Bookshelf bookShelf2= shelfValue();
        try{
            System.out.println(bookShelf1.equals(bookShelf2) ? "These shelves are equal" : "These shelves are not equal");
        }catch (Exception e){
            System.out.println("Unexpected Error! Try Again!");
        }
    }

    /**
     * This method displays the instructions necessary to print the contents of the Bookshelf.
     */
    private static void printInstructions() throws Exception {
        System.out.println("Bookshelf " + bookShelfValue + ":");
        System.out.println("Spot      Title                                                Author              Condition          Borrower" );
        System.out.println("----------------------------------------------------------------------------------------------------------------------");

        for(int i = 0 ; i < shelfRow.CAPACITY; i++){
            String borrower = "<none>";
            if(shelfRow.getBook(i) != null){
                if(shelfRow.getBook(i).getBorrower() == null){
                    borrower = "<none>";
                }else{
                    borrower = shelfRow.getBook(i).getBorrower();
                }
            }

            if(shelfRow.getBook(i) != null){
                System.out.println();
                System.out.printf("%d",i+1);
                System.out.print(". ");
                System.out.printf("%-54s%-30s%-15s%-20s ",shelfRow.getBook(i).getTitle(), shelfRow.getBook(i).getAuthor(),
                        shelfRow.getBook(i).getCondition(),borrower);
            }
        }
    }
    /**
     * This method displays the instructions necessary to quit all viewing options.
     */
    private static void quitInstructions() {
        quitValue = true;
        System.out.print("Goodbye!");
    }
}