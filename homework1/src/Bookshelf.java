//Jimmy Zhang ID: 112844431 CSE 214 R02

import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * This class represents a Bookshelf that implements the Cloneable interface.
 * It has a Capacity of the maximum number of bookshelf Book[] to store books in an array and a count variable.
 * @author Jimmy Zhang
 */
public class Bookshelf implements Cloneable {
    final static int CAPACITY = 20;
    private Book[] books = new Book[CAPACITY];
    private int count = 0;

    public Bookshelf(){}

    public int numBooks(){
        return count;
    }

    /**
     * This method is used to get the book in the bookshelf when a specified index is given.
     * @param index
     *      Specified position of the book on the shelf
     * @return
     */
    public Book getBook(int index) throws Exception {
        try{
            return books[index];
        }catch  (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("Array Index is greater than the max CAPACITY of: " + CAPACITY);
        }catch (Exception e){
            throw new Exception("There is an Error! Try Again!");
        }
    }

    /**
     * This method is used to remove a book from the bookshelf when a specified index is given.
     * @param index
     *      Specified position of the book on the shelf
     */

    public void removeBook(int index) throws Exception {
        // Assuming max 20 books, the index goes from 0-19, so index "20" would be out of bounds.
        if(index >= CAPACITY){
            throw new ArrayIndexOutOfBoundsException("Array Index is greater than the max CAPACITY of: " + CAPACITY);
        }
        if(numBooks() == 0){
            throw new EmptyShelfException("The bookshelf is empty!");
        }
        Book[] newBook = new Book[CAPACITY];
        try{
            for(int i = 0; i < index; i++){
                newBook[i] = books[i];
            }

            for(int j = index; j <= books.length-2; j++){
                newBook[j] = books[j+1];
            }
            books[books.length-1] = null;
            books = newBook;
            count--;
            System.out.println("Book Removed!");
        }catch (Exception e){
            throw new Exception("There is an error!");
        }
    }

    /**
     * This method is used to remove add a book from the bookshelf when a specified index is given and the Book object itself.
     * @param index
     *      Specified position of the book on the shelf
     * @param book
     *      The book object
     */
//
    public void addBook(int index, Book book) throws Exception {
        if(numBooks() >= CAPACITY){
            throw new FullShelfException("The shelf is full!");
        }
        try {
            Book[] newBooks = new Book[CAPACITY];
            for (int i = 0; i < index; i++) {
                newBooks[i] = books[i];
            }
            newBooks[index] = book;
            for (int j = index + 1; j < books.length; j++) {
                newBooks[j] = books[j - 1];
            }
            books = newBooks;
            count++;
            System.out.println();
            System.out.println("Book added!");
        }catch(ArrayIndexOutOfBoundsException e){
            //Illegal Argument Exception is a superset of ArrayOut of bounds, so calling it is inclusive.
            throw new IllegalArgumentException("Array Index is greater than the max CAPACITY of: " + CAPACITY);
        }catch(InputMismatchException e){
            throw new InputMismatchException("Input mismatch exception");
        }
        catch(Exception e){
            throw new Exception("There is some unknown exception");
        }

        //    Adds a book at the given index, moving all books at or after the given index one index to the right.
        //    Throws an IllegalArgumentException if the index is too high and would create a hole in the array
        //    Throws a FullShelfException if more than 20 books are added to the shelf (you must write this, just make an empty class that extends Exception) if the array is full.
    }


    /**
     * This method takes two books and swaps them
     * @param index1
     *      Specified position of a book on the shelf
     * @param index2
     *      Specified position of another book on the shelf
     */
    public void swapBooks(int index1, int index2){
        try{
            if(books[index1] != null && books[index2] != null){
                Book temp;
                temp = books[index1];
                books[index1] = books[index2];
                books[index2] = temp;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("Index is invalid");
        }catch(Exception e){
            System.out.println("Invalid, Try Again!");
        }

    }

    /**
     * This method takes the bookshelf and duplicates it
     * @return
     * @throws CloneNotSupportedException
     */
    public Bookshelf clone() throws CloneNotSupportedException {
        try{
            Bookshelf bookshelf = (Bookshelf) (super.clone());
            return bookshelf;

        }catch (CloneNotSupportedException e){
            throw new CloneNotSupportedException("Can't Clone");
        }
    }

    /**
     * This method compares two bookshelves and determines if they have the same book content
     * @param obj
     * @return
     */
    public boolean equals(Object obj){
        if(obj instanceof Bookshelf){
            Bookshelf bookshelf = (Bookshelf) obj;
            for(int i = 0; i < books.length;i++){
                Book book = null;
                try {
                    book = bookshelf.getBook(i);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if(books[i].getTitle().equals(book.getTitle()) &&
                        books[i].getAuthor().equals(book.getAuthor()) &&
                        books[i].getCondition()== book.getCondition()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Bookshelf{" +
                "CAPACITY=" + CAPACITY +
                ", books=" + Arrays.toString(books) +
                ", count=" + count +
                '}';
    }

    /**
     * This method is created to thrown an EmptyShelfException
     */
    public static class EmptyShelfException extends Exception{
        public EmptyShelfException(String errorMessage) {
            super(errorMessage);
        }
    }
    /**
     * This method is created to thrown an FullShelfException
     */
    public static class FullShelfException extends Exception{
        public FullShelfException(String errorMessage) {
            super(errorMessage);
        }
    }
}

