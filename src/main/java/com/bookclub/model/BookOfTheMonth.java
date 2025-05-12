/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a monthly featured book entry in the BookClub application.
 */
@Document("bookOfTheMonth")
public class BookOfTheMonth {

    @Id
    private String id;

    private Integer month;

    @NotEmpty(message = "ISBN is a required field.")
    private String isbn;

    // Default constructor
    public BookOfTheMonth() {}

    // Constructor
    public BookOfTheMonth(String isbn, Integer month) {
        this.isbn = isbn;
        this.month = month;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // String format similar to WishlistItem
    @Override
    public String toString() {
        return String.format("BookOfTheMonth{id=%s, isbn=%s, month=%d}", id, isbn, month);
    }
}
