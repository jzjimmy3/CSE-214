//Jimmy Zhang ID: 112844431 CSE 214 R02
//Q : For the equals method , when comparing A and C Bookshelves,here is a null pointer error because object is
// empty,is there a way to check if object is empty, and if so don't compare Shelf A and C
//Q: How to print null as none?
//Q: How to do clone in java?
//Q: Do I keep the custom Exceptions at the bottom or the top?

import java.util.Scanner;

public class RipoffRental {
    private static String menuOptionsValue, bookShelfValue;
    private static boolean  quitValue;
    private static Bookshelf bookShelfNum;
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
        while(quitValue != true)
            chooseMenuOptions();
    }

    public static void menuOptions(){
        System.out.println("Welcome to Jack's aMAzin' Textbook Rentals, highest price guaranteed!");
        System.out.print("Please enter a book shelf for viewing[A,B,C]: ");
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

    /**
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

    public static void addBookInstructions(){
        System.out.print("Please enter a condition: ");
        int conditionInput = input.nextInt();
        System.out.print("Please enter a position on shelf (0 is first position): ");
        int positionInput = input.nextInt();
        System.out.print("Please enter a title: ");
        String titleInput = input.next();
        System.out.print("Please enter a author: ");
        String authorInput= input.next();

        Book book = new Book(titleInput,authorInput,conditionInput);
        try{
            bookShelfNum.addBook(positionInput,book);
        }catch(Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }

    private static void swapBookInstructions() {
        System.out.println("Please enter an index: ");
        int index1Input = input.nextInt();
        System.out.println("Please enter another index: ");
        int index2Input = input.nextInt();
        bookShelfNum.swapBooks(index1Input,index2Input);
    }

    private static void loanBookInstructions() throws Exception {
        System.out.print("Please enter a recipient: ");
        String recipient = input.nextLine();
        System.out.print("Please enter an index: ");
        int bookIndex = input.nextInt();
        System.out.print("Please enter condition(1-5): ");
        int condition = input.nextInt();

        bookShelfNum.getBook(bookIndex).setBorrower(recipient);
        bookShelfNum.getBook(bookIndex).setCondition(condition);
    }

    private static void removeBookInstructions() throws Exception {
        System.out.println("Please enter an index to remove book: ");
        int indexInput = input.nextInt();
        try{
            bookShelfNum.removeBook(indexInput);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private static void duplicateBookInstructions() throws Exception {
        System.out.print("Please enter a source index: ");
        int sourceIndex = input.nextInt();
        System.out.print("Please enter a destination index: ");
        int destinationIndex = input.nextInt();

        Book elem = bookShelfNum.getBook(sourceIndex);
        try{
            bookShelfNum.addBook(destinationIndex, new Book(elem.getTitle(), elem.getAuthor(),elem.getCondition()));
        }catch(Exception e){
            System.out.println("Catching Error");
        }

//        Scanner input = new Scanner(System.in);
//        System.out.print("Please enter a source index: ");
//        int sourceIndex = input.nextInt();
//        System.out.print("Please enter a destination index: ");
//        int destinationIndex = input.nextInt();
//
//        Book elem = bookShelfNum.getBook(sourceIndex);
//        bookShelfNum.addBook(bookShelfNum.getBook(sourceIndex).clone());

    }

    private static void changeShelfInstructions() {
        System.out.print("Please enter a book shelf for viewing[A,B,C]: ");
        bookShelfNum = shelfValue();
    }

    private static void overwriteInstructions() throws CloneNotSupportedException {
        System.out.println("Please select shelf to overwrite: ");
        Bookshelf bookshelf = bookShelfNum;
        Bookshelf bookshelfCopy = bookshelf.clone();
        Bookshelf bookshelfReplace = shelfValue();
        bookshelfReplace = bookshelfCopy;
        System.out.println(bookshelfReplace);
    }

    private static void equalInstructions() {
        System.out.print("Please Select a shelf: ");
        Bookshelf bookShelf1= shelfValue();
        System.out.print("Please Select another shelf: ");
        Bookshelf bookShelf2= shelfValue();
        System.out.println(bookShelf1.equals(bookShelf2));
    }

    private static void printInstructions() throws Exception {
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
