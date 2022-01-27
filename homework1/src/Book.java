//Jimmy Zhang ID: 112844431 CSE 214

public class Book {
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
}
