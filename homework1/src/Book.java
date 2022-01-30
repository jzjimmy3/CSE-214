//Jimmy Zhang ID: 112844431 CSE 214

import java.util.Objects;

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
//    public boolean equals(Object obj){
//        if(obj instanceof Book){
//            if(((Book) obj).author == this.author  && ((Book) obj).title == this.title){
//                return true;
//            }
//        }
//        return false;
//    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return condition == book.condition && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(borrower, book.borrower);
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
