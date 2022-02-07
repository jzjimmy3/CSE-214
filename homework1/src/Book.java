//Jimmy Zhang ID: 112844431 CSE 214 R02

/**
 * This class represents a Book that implements the Cloneable interface.
 * It has a title, author, borrower, and condition associated with it.
 * @author Jimmy Zhang
 */
public class Book implements Cloneable{
    private String title, author, borrower;
    private int condition;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getBorrower() { return borrower; }
    public void setBorrower(String borrower) { this.borrower = borrower; }

    public int getCondition() { return condition; }
    public void setCondition(int condition) { this.condition = condition; }

    public Book(){}

    /**
     * This is a constructor used to create a new Book Object
     * @param title
     *  The title of book
     * @param author
     *  The author of book
     * @param condition
     *  The condition the book is in
     */
    public Book(String title, String author, int condition) {
        this.title = title;
        this.author = author;
        this.condition = condition;
    }

    /**
     * @return
     * @throws CloneNotSupportedException
     */
    public Book clone() throws CloneNotSupportedException {
        Book newBook = (Book) (super.clone());
        return newBook;
    }

    /**
     * @param obj
     * @return
     */
    public boolean equals(Object obj){
        if(obj instanceof Book){
            Book book = (Book) obj;
            if(condition == book.condition && title.equals(book.title) && author.equals(book.author) && borrower.equals(book.borrower)){
                return true;
            }
        }
        return false;
    }

    /**
     * @return
     */
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", borrower='" + borrower + '\'' +
                ", condition=" + condition +
                '}';
    }
}
