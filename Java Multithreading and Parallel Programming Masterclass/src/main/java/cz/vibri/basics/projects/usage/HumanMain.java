package cz.vibri.basics.projects.usage;

public class HumanMain {

    public static void main(String[] args) {
        Human human = new Human();
        human.setSurname("Orel");
        human.setFirstName("Karel");

        System.out.println(human.getName() + " " + human.getSurname());

        FirstName firstName = human;
        System.out.println(firstName.getName());

        Surname surname = human;
        System.out.println(surname.getSurname());
    }
}
