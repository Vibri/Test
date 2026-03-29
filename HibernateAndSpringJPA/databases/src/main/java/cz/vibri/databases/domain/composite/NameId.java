package cz.vibri.databases.domain.composite;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class NameId implements Serializable {

    private String firstName;
    private String lastName;

    public NameId(String fisrtName, String lastName) {
        this.firstName = fisrtName;
        this.lastName = lastName;
    }

    public NameId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameId nameId = (NameId) o;
        return Objects.equals(firstName, nameId.firstName) && Objects.equals(lastName, nameId.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
