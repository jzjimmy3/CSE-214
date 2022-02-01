//Jimmy Zhang ID: 112844431 CSE 214 R02

import java.util.Arrays;

public class Bookshelf {
    final int CAPACITY = 20;
    private Book[] books = new Book[CAPACITY];
    private int count = 0;
    public Bookshelf(){}

    /**
     * @param books
     */
    public Bookshelf(Book[] books) {
        this.books = books;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Bookshelf myBookShelf = new Bookshelf();
        Book myBook1 = new Book("Harry Potter1", "JK Rowling1", 1);
        Book myBook2 = new Book("Harry Potter2", "JK Rowling2", 2);
        Book myBook3 = new Book("Harry Potter3", "JK Rowling3", 3);
        Book myBook4 = new Book("Harry Potter4", "JK Rowling4", 4);
        Book myBook5 = new Book("Harry Potter5", "JK Rowling5", 5);
        Book myBook6 = new Book("Harry Potter6", "JK Rowling6", 6);
        myBookShelf.addBook(0,myBook1);
        myBookShelf.addBook(1,myBook2);
        myBookShelf.addBook(2,myBook3);
        myBookShelf.addBook(1,myBook4);
        System.out.println(myBookShelf.toString());
        int numBooks = myBookShelf.numBooks();
        System.out.println("The num of Books are:" + numBooks);
        myBookShelf.removeBook(1);
        System.out.println(myBookShelf.toString());
        System.out.println("GET BOOK: " + myBookShelf.getBook(2));
        myBookShelf.swapBooks(0,1);
        System.out.println(myBookShelf.toString());
    }

    /**
     * @return
     */
    public int numBooks(){
        // How do I make this O(1)??
        int numOfBooks = 0;
        for(int i = 0; i < books.length; i++){
            // if book[i] != null it means that there is a book there.
            if(books[i] != null){
                numOfBooks++;
            }
        }
        return numOfBooks;
        //Returns the total number of books on the shelf.
        // This method should run in O(1) time.
    }

    /**
     * @param index
     * @return
     */
    public Book getBook(int index){
        if(index >= CAPACITY){
            throw new ArrayIndexOutOfBoundsException("Array Index is greater than the max CAPACITY of: " + CAPACITY);
        }
        return books[index];

//    Gets the reference to the Book at the given index
//    Throws an ArrayIndexOutOfBoundsException if the index is invalid
//    The list should be unchanged
    }

    /**
     * @param index
     */
//
    public void removeBook(int index){
        // Assuming max 20 books, the index goes from 0-19, so index "20" would be out of bounds.
        if(index >= CAPACITY){
            throw new ArrayIndexOutOfBoundsException("Array Index is greater than the max CAPACITY of: " + CAPACITY);
        }
//        if(numBooks() == 0){
//            throw new EmptyShelfException();
//        }
        Book[] newBook = new Book[CAPACITY];
        for(int i = 0; i < index; i++){
            newBook[i] = books[i];
        }
        //[1,2,3]
        //[1,3,null]

        for(int j = index; j <= books.length-2; j++){
            newBook[j] = books[j+1];
        }
        books[books.length-1] = null;
        books = newBook;
        System.out.println("Book Removed!");

//        Gets the reference to the Book at the given index
//        Throws an ArrayIndexOutOfBoundsException if the index is invalid
//        Throws an EmptyShelfException(you must write this, an empty class extending Exception is the best way to do this) if there is no book on the shelf.
//        Removes the given book and moves all books to the right of it leftwards by one index
    }

    /**
     * @param index
     * @param book
     */
//
    public void addBook(int index, Book book){
        if(index >= CAPACITY){
            throw new IllegalArgumentException("Array Index is greater than the max CAPACITY of: " + CAPACITY);
        }
        Book[] newBooks = new Book[CAPACITY];
        for(int i = 0; i < index; i++){
            newBooks[i] = books[i];
        }
        newBooks[index] = book;
        for(int j = index + 1; j < books.length; j++){
            newBooks[j] = books[j - 1];
        }
        books = newBooks;
        System.out.println("Book added!");

        //    Adds a book at the given index, moving all books at or after the given index one index to the right.
        //    Throws an IllegalArgumentException if the index is too high and would create a hole in the array
        //    Throws a FullShelfException if more than 20 books are added to the shelf (you must write this, just make an empty class that extends Exception) if the array is full.
    }

    /**
     * @param index1
     * @param index2
     */
    public void swapBooks(int index1, int index2){
        if(index1 >= CAPACITY || index2 >= CAPACITY){
            throw new IllegalArgumentException("Array Index is greater than the max CAPACITY of: " + CAPACITY);
        }
        if(books[index1] != null && books[index2] != null){
            Book temp;
            temp = books[index1];
            books[index1] = books[index2];
            books[index2] = temp;
        }
        System.out.println("Book Swapped");

        //    If the indices are valid, the two books are swapped.
        //    Throws an ArrayIndexOutOfBoundsException if either index is invalid
        //    The list should be unchanged if either index was invalid
    }
//
//    public Object clone(){
//        //        Creates a deep copy of this Bookshelf object (all the books are copied individually (note: the borrower field should be empty in the clone!) and placed on a new Bookshelf in the same order)
//        //    If the copy is modified, this object should remain unmodified.
//    }
//    public boolean equals(Object o){
//        // Checks if this Bookshelf is equal to another object (equal books in the same order)
//    }
//    public String toString(){
//        // You must write a toString() method. You can use this method to help you with printing the Bookshelf for the output.
//    }

//
    class EmptyShelfException extends Exception{

    }
//    class FullShelfException extends Exception{
//
//    }

//    public Object clone(){
//
//    }

    /**
     * @param obj
     * @return
     */
    public boolean equals(Object obj){
        if(obj instanceof Bookshelf){
            Bookshelf bookshelf = (Bookshelf) obj;
            for(int i = 0; i < books.length;i++){
                Book book = bookshelf.getBook(i);
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
}

