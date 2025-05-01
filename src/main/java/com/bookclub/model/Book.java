/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a book in the BookClub application.
 * This class stores details about a book, including its ISBN, title, description,
 * number of pages, and list of authors.
 */
public class Book {
    private String isbn;
    private String title;
    private String description;
    private String infoUrl;
    private int numOfPages;

    // Default Constructor
    public Book() {}

    /**
     * Parameterized constructor.
     * Initializes the book with the provided values.
     * @param isbn        The ISBN of the book.
     * @param title       The title of the book.
     * @param description A brief description of the book.
     * @param infoUrl     The Url for book info.
     * @param numOfPages  The number of pages in the book.
     */
    public Book(String isbn, String title, String description, String infoUrl, int numOfPages) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.infoUrl = infoUrl;
        this.numOfPages = numOfPages;
    }

    /**
     * Parameterized constructor.
     * Initializes the book with the provided values.
     * @param isbn        The ISBN of the book.
     * @param title       The title of the book.
     * @param infoUrl     The Url for book info.
     */
    public Book(String isbn, String title, String infoUrl) {
        this.isbn = isbn;
        this.title = title;
        this.infoUrl = infoUrl;
    }

    // Getters and Setters for book details
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    // Override toString method to return a formatted string containing book details.
    @Override
    public String toString() {
        return "Book{isbn=" + isbn + ", title=" + title + ", description=" + description +
                ", infoUrl=" + infoUrl + ", numOfPages=" + numOfPages + "}";
    }
}
