package com.library_mediator.mediator;

import com.library_mediator.colleague.Student;
import com.library_mediator.model.Book;

/**
 * Mediator that coordinates communication between students and books.
 */
public interface Mediator {
    /**
    * Notifies the mediator of a student's action related to a book.
     *
    * @param student student performing the action
    * @param book book related to the action
    * @param action action performed
     */
    void notify(Student student, Book book, String action);
}
