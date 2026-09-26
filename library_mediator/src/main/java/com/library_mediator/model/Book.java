package com.library_mediator.model;
/**
 * Represents a book in the library, including its title, author, and current
 * circulation status.
 *
 * <p>A newly created book has the {@code Available} status. Supported statuses
 * are {@code Available}, {@code Borrowed}, and {@code Reserved}.</p>
 */

import java.util.List;

public class Book {

    private List<String> correctStatus = List.of("Available", "Borrowed", "Reserved");
        /** The statuses accepted for this book. */
    private String title;
    private String author;
    private String status; // Available, Borrowed, Reserved

    public Book(String title, String author) {
            /**
             * Creates a book with the given title and author. Its initial status is
             * {@code Available}.
             *
             * @param title the book's title
             * @param author the book's author
             */
        this.title = title;
        this.author = author;
        this.status = "Available";
    }

    public String getTitle() {
            /**
             * Returns the book's title.
             *
             * @return the title
             */
        return title;
    }

    public String getAuthor() {
            /**
             * Returns the book's author.
             *
             * @return the author
             */
        return author;
    }

    public String getStatus() {
            /**
             * Returns the book's current status.
             *
             * @return the current status
             */
        return status;
    }

    public void setStatus(String status) {
            /**
             * Changes the book's status.
             *
             * @param status the new status; must be {@code Available}, {@code Borrowed},
             *                or {@code Reserved}
             * @throws IllegalArgumentException if the status is not supported
             */
        if (!correctStatus.contains(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        this.status = status;
    }


}
