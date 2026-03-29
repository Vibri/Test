package cz.vibri.concurrency.studentsInLibrary;

import java.util.Random;

public class Student implements Runnable {

    private int id;
    private Book[] books;
    private Random random;

    public Student(int id, Book[] books) {
        this.id = id;
        this.random = new Random();
        this.books = books;
    }

    @Override
    public void run() {
        while (true) {
            int bookId = random.nextInt(StudentsConstants.NUM_OF_BOOKS);

            try {
                books[bookId].read(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return "Student" +
                " #" + id;
    }
}
