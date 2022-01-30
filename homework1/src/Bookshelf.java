//Jimmy Zhang ID: 112844431 CSE 214

import java.util.Arrays;

public class Bookshelf {
    final int CAPACITY = 2;
    private Book[] books = new Book[CAPACITY];
    private int count = 0;
    public Bookshelf(){}

    public static void main(String[] args) {
        Bookshelf myBookShelf = new Bookshelf();
        Book myBook1 = new Book("Harry Potter1", "JK Rowling1", 1);
        Book myBook2 = new Book("Harry Potter2", "JK Rowling2", 2);
        Book myBook3 = new Book("Harry Potter3", "JK Rowling3", 3);
        myBookShelf.addBook(0,myBook1);
        System.out.println(myBookShelf.toString());
        int numBooks = myBookShelf.numBooks();
        System.out.println("The num of Books are:" + numBooks);
    }

    public int numBooks(){
        // How do I make this O(1)??
        int numOfBooks = 0;
        System.out.println(books.length);
        System.out.println(books[0]);
        for(int i = 0; i < books.length-1; i++){
            if(books[i].getBorrower() == null){
                numOfBooks++;
                System.out.println("NumOf Books Status");
            }
        }
        return numOfBooks;
        //Returns the total number of books on the shelf.
        // This method should run in O(1) time.
    }

//    public Book getBook(int index){
//        Book[] addBooks = new Book[CAPACITY];
//        System.arraycopy(books,0,addBooks, index-1,index);
//        System.arraycopy(books,index+1,addBooks,index-1, books.length);
//        if(index > CAPACITY){
//            throw new ArrayIndexOutOfBoundsException("Array Index Out of Bounds");
//        }
//        else{
//            return books[index];
//        }
////    Gets the reference to the Book at the given index
////    Throws an ArrayIndexOutOfBoundsException if the index is invalid
////    The list should be unchanged
//    }
//
//    public Book removeBook(int index){
//        if(index > CAPACITY){
//            throw new ArrayIndexOutOfBoundsException("Array Index Out of Bounds");
//        }
//        for(int i = index; i < books.length-1; i++){
//            books[i] = books[i+1];
//        }
//        books[books.length-1] = null;
////        Gets the reference to the Book at the given index
////        Throws an ArrayIndexOutOfBoundsException if the index is invalid
////        Throws an EmptyShelfException(you must write this, an empty class extending Exception is the best way to do this) if there is no book on the shelf.
////        Removes the given book and moves all books to the right of it leftwards by one index
//    }
//
    public void addBook(int index, Book book){
        if(index >= CAPACITY){
            throw new IllegalArgumentException("Array Index is greater than the max CAPACITY of: " + CAPACITY);
        }
        Book[] newBooks = new Book[CAPACITY];
        for(int i = 0; i < index; i++){
            newBooks[i] = books[i];
        }
        for(int j = index + 1; j < books.length - 1; j++){
            books[j] = book;
        }
        newBooks[index] = book;
        books = newBooks;

        //    Adds a book at the given index, moving all books at or after the given index one index to the right.
        //    Throws an IllegalArgumentException if the index is too high and would create a hole in the array
        //    Throws a FullShelfException if more than 20 books are added to the shelf (you must write this, just make an empty class that extends Exception) if the array is full.
    }
//    public void swapBooks(int index1, int index2){
//        //    If the indices are valid, the two books are swapped.
//        //    Throws an ArrayIndexOutOfBoundsException if either index is invalid
//        //    The list should be unchanged if either index was invalid
//    }
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
//    class EmptyShelfException extends Exception{
//
//    }
//    class FullShelfException extends Exception{
//
//    }
@Override
public String toString() {
    return "Bookshelf{" +
            "CAPACITY=" + CAPACITY +
            ", books=" + Arrays.toString(books) +
            ", count=" + count +
            '}';
}
}

