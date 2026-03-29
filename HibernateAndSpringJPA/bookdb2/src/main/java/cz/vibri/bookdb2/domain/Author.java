package cz.vibri.bookdb2.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "author_find_all", query = "FROM Author"),
        @NamedQuery(name = "find_by_name", query = "FROM Author a WHERE a.firstName = :first_name and a.lastName = :last_name"),
        @NamedQuery(name = "find_by_last_name_and_sort_by_first_name_desc", query = "FROM Author a WHERE a.lastName = :last_name ORDER BY a.firstName DESC"),
        @NamedQuery(name = "find_by_last_name_and_sort_by_first_name_asc", query = "FROM Author a WHERE a.lastName = :last_name ORDER BY a.firstName ASC")

})
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Transient
    private List<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
