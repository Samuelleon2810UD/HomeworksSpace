package com.library_mediator;

import java.util.List;

import com.library_mediator.colleague.Student;
import com.library_mediator.mediator.LibraryMediator;
import com.library_mediator.model.Book;

/**
 * Entry point for the library mediator simulation.
 *
 * <p>This class creates the registered students and library books, initializes
 * the mediator, and runs a complete borrowing and returning cycle for each
 * student.</p>
 */
public class Main {
    /** Registered students allowed to borrow books from the library. */
    private static final List<Student> STUDENTS = List.of(
        new Student("Ana García"),
        new Student("Bruno Martínez"),
        new Student("Carla Rodríguez"),
        new Student("Diego López"),
        new Student("Elena Sánchez")
    );

    /** Books managed by the library mediator. */
    private static final List<Book> BOOKS = List.of(
        new Book("Cien años de soledad", "Gabriel García Márquez"),
        new Book("Don Quijote de la Mancha", "Miguel de Cervantes"),
        new Book("1984", "George Orwell"),
        new Book("El principito", "Antoine de Saint-Exupéry"),
        new Book("La sombra del viento", "Carlos Ruiz Zafón")
    );

    /** Mediator responsible for coordinating borrowing and returning operations. */
    private static final LibraryMediator libraryMediator =
        new LibraryMediator(STUDENTS, BOOKS);

    /** Prevents instantiation of this entry-point class. */
    private Main() {
    }

    /**
     * Runs the library simulation.
     *
     * <p>Each registered student borrows one book and then returns it. The
     * final status of every book and the number of borrowed books per student
     * are printed to the console.</p>
     *
     * @param args command-line arguments; they are not used by the simulation
     */
    public static void main(String[] args) {
        System.out.println("=== Sistema de biblioteca ===");
        System.out.println("[Sistema] Se inicia el mediador de la biblioteca.");

        System.out.println("\n--- Prestamos coordinados por el mediator ---");
        for (int index = 0; index < STUDENTS.size(); index++) {
            libraryMediator.notify(STUDENTS.get(index), BOOKS.get(index), "borrow");
        }

        System.out.println("\n--- Devoluciones coordinadas por el mediator ---");
        for (int index = 0; index < STUDENTS.size(); index++) {
            libraryMediator.notify(STUDENTS.get(index), BOOKS.get(index), "return");
        }

        System.out.println("\n--- Estado final ---");
        for (Book book : BOOKS) {
            System.out.println(book.getTitle() + " -> " + book.getStatus());
        }
        for (Student student : STUDENTS) {
            System.out.println(student.getName() + " tiene "
                    + student.getBorrowedBooks().size() + " libro(s) prestado(s).");
        }
    }
}