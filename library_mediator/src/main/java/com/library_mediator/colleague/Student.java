package com.library_mediator.colleague;

import java.util.ArrayList;
import java.util.List;

import com.library_mediator.mediator.Mediator;
import com.library_mediator.model.Book;


/**
 * Representa a un estudiante que puede tomar libros prestados de la biblioteca.
 */
public class Student {

    private Mediator mediator;

    /** Nombre del estudiante. */
    private String name;

    /** Lista de libros prestados por el estudiante. */
    private List<Book> borrowedBooks;

    /**
     * Crea un estudiante con el nombre indicado.
     *
     * @param name nombre del estudiante
     */
    public Student(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del estudiante.
     *
     * @return nombre del estudiante
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene los libros prestados por el estudiante.
     *
     * @return lista de libros prestados
     */
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    /**
     * Presta un libro al estudiante si está disponible.
     *
     * @param book libro que se desea tomar prestado
     */
    public void borrowBook(Book book) {
        if (book.getStatus().equals("Available")) {
            borrowedBooks.add(book);
            book.setStatus("Borrowed");
            System.out.println(name + " borrowed the book: " + book.getTitle());
        } else {
            System.out.println("Sorry, the book: " + book.getTitle() + " is not available.");
        }
    }

    public void send(Book book , String action) {
        mediator.notify(this, book, action);
    }
}
