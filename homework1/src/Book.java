//Jimmy Zhang ID: 112844431 CSE 214 R02

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

    public Book(String title, String author, int condition) {
        this.title = title;
        this.author = author;
        this.condition = condition;
    }

    public Book clone(){
        Book book = new Book(this.title,this.author,this.condition);
        Book newBook = book.clone();
        return newBook;
    }

    public boolean equals(Object obj){
        if(obj instanceof Book){
            Book book = (Book) obj;
            if(condition == book.condition && title.equals(book.title) && author.equals(book.author) && borrower.equals(book.borrower)){
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", borrower='" + borrower + '\'' +
                ", condition=" + condition +
                '}';
    }
}
