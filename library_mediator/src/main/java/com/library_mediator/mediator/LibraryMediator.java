package com.library_mediator.mediator;

import java.util.List;

import com.library_mediator.colleague.Student;
import com.library_mediator.model.Book;

/**
 * Mediator responsible for coordinating book borrowing and returning operations
 * between students and the library.
 * <p>
 * This class validates that the student is registered, that the book is in a
 * state compatible with the requested action, and that the correct relationship
 * exists between the student and the book before executing the operation.
 * </p>
 *
 * @author Samuel
 * @version 1.0
 */
public class LibraryMediator implements Mediator {
    private List<Student> students;
    private List<Book> books;

    public LibraryMediator(List<Student> students, List<Book> books) {
        this.students = students;
        this.books = books;
    }

    /**
    * Executes the indicated action on a book in relation to a student.
     *
    * @param student student performing the operation
    * @param book book involved in the operation
    * @param action action to perform; can be "borrow" or "return"
    * @throws IllegalArgumentException if the student is not registered
    * @throws IllegalStateException if the student-book relationship or the
    * book's state does not allow the action to be executed
     */
    public void notify(Student student, Book book, String action) {

        validateStudent(student);
        validateStudentBookRelation(student, book, action);
        validateBookStatus(book, action);

        if (action.equals("borrow")) {
            updateBookStatus(book, action);
            moveBookBetweenLists(student, book, action);
            System.out.println(student.getName() + " borrowed the book: " + book.getTitle());
        } else if (action.equals("return")) {
            updateBookStatus(book, action);
            moveBookBetweenLists(student, book, action);
            System.out.println(student.getName() + " returned the book: " + book.getTitle());
        }
    }

    /**
    * Verifies that the book is in a valid state for the indicated action.
     *
    * @param book book to evaluate
    * @param action action to perform
    * @throws IllegalStateException if the book is not available for borrowing
    * or is not borrowed for returning
     */
    private void validateBookStatus(Book book, String action) {
        if (action.equals("borrow") && !book.getStatus().equals("Available")) {
            throw new IllegalStateException("The book is not available for borrowing.");
        } else if (action.equals("return") && !book.getStatus().equals("Borrowed")) {
            throw new IllegalStateException("The book is not currently borrowed.");
        }
    }

    /**
    * Verifies that the student is registered in the library.
     *
    * @param student student to validate
    * @throws IllegalArgumentException if the student is not in the list of
    * registered students
     */
    private void validateStudent(Student student) {
        if (!students.contains(student)) {
            throw new IllegalArgumentException("The student is not registered in the library.");
        }
    }

    /**
    * Verifies that the student has borrowed the book before returning it.
     *
    * @param student student associated with the operation
    * @param book book to return
    * @param action action being performed
    * @throws IllegalStateException if the student does not have this book in
    * their list of borrowed books
     */
    private void validateStudentBookRelation(Student student, Book book, String action) {
        if (action.equals("return") && !student.getBorrowedBooks().contains(book)) {
            throw new IllegalStateException("The student did not borrow this book.");
        }
    }

    /**
    * Updates the book's status according to the requested action.
     *
    * @param book book whose status will be updated
    * @param action action performed: borrowing or returning
     */
    private void updateBookStatus(Book book, String action) {
        if (action.equals("borrow")) {
            book.setStatus("Borrowed");
        } else if (action.equals("return")) {
            book.setStatus("Available");
        }
    }

    /**
    * Moves the book between the student's collection and the library's general
    * book collection according to the action.
     *
    * @param student student associated with the operation
    * @param book book whose status and collection change
    * @param action action to perform: borrowing or returning
     */
    private void moveBookBetweenLists(Student student, Book book, String action) {
        if (action.equals("borrow")) {
            student.getBorrowedBooks().add(book);
            book.setStatus("Borrowed");
            this.books.get(this.books.indexOf(book)).setStatus("Borrowed");
        } else if (action.equals("return")) {
            student.getBorrowedBooks().remove(book);
            book.setStatus("Available");
            this.books.get(this.books.indexOf(book)).setStatus("Available");
        }
    }

}
