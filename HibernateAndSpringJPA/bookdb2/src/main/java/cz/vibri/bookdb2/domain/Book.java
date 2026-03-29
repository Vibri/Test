package cz.vibri.bookdb2.domain;

import jakarta.persistence.*;

import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "book_find_by_isbn", query = "FROM Book b WHERE b.isbn = :isbn"),
        @NamedQuery(name = "book_find_by_title", query = "FROM Book b where b.title = :title"),
        @NamedQuery(name = "book_find_all", query = "FROM Book"),
        @NamedQuery(name = "Book.jpaNamed", query = "FROM Book b where b.title = :title"),

}
)
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;
    private String publisher;
    @Transient
    private Author author;

    public Book() {
    }

    public Book(String title, String isbn, String publisher, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(isbn, book.isbn) && Objects.equals(publisher, book.publisher) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn, publisher, author);
    }
}
