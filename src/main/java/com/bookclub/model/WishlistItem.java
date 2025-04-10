/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a wishlist item in the BookClub application.
 * This class stores details about a wishlist item, including its ISBN and title.
 */
@Document("wishlist")
public class WishlistItem {
    @NotNull
    @NotEmpty(message = "ISBN is a required field.")
    private String isbn;

    @NotNull
    @NotEmpty(message = "Title is a required field.")
    private String title;

    @Id
    private String id;

    // Default Constructor
    public WishlistItem() {
    }

    /**
     * Parameterized constructor.
     * Initializes the wishlist item with the provided values.
     * @param isbn        The ISBN of the book.
     * @param title       The title of the book.
     */
    public WishlistItem(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    // Getters and Setters for wishlist item details
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    // Override toString method to return a formatted string containing book details.
    @Override
    public String toString() {
        return String.format("WishlistItem{id=%s, isbn=%s, title=%s}", id, isbn, title);
    }
}