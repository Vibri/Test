package cz.vibri.basics.projects.usage;

public class Human implements FirstName,Surname{

    private String firstName;
    private String surname;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getName() {
        return this.firstName;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }
}
