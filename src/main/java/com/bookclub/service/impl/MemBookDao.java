/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

/**
 * A simple in-memory implementation of the BookDao interface.
 * This class maintains a static list of books and provides methods to retrieve them.
 */
public class MemBookDao implements BookDao {

    // List to store book objects in memory
    private final List<Book> books;

    // Constructor to initialize the book list with predefined books.
    public MemBookDao() {
        this.books = new ArrayList<Book>();

        this.books.add(new Book("9780544003415", "The Lord Of The Rings",
            "An epic fantasy trilogy that follows Frodo Baggins as he embarks on a perilous journey to destroy the One Ring and defeat the dark lord Sauron, set in the richly detailed world of Middle-earth.",
            1216, Arrays.asList("J.R.R. Tolkien")));

        this.books.add(new Book("9780425269404", "Patriot Games",
            "A thrilling spy novel featuring Jack Ryan, who thwarts an assassination attempt by an Irish terrorist group, only to find himself and his family targeted in a dangerous revenge plot.",
            800, Arrays.asList("Tom Clancy")));

        this.books.add(new Book("9781501192272", "The Talisman",
            "A young boy embarks on a mystical quest across parallel worlds to save his dying mother, encountering danger, magic, and dark forces along the way.",
            784, Arrays.asList("Stephen King", "Peter Straub")));

        this.books.add(new Book("9780446676090", "The Notebook",
            "A heartwarming romance novel about Noah and Allie, two lovers from different social backgrounds, whose love endures years of separation, heartbreak, and the test of time.",
            272, Arrays.asList("Nicholas Sparks")));

        this.books.add(new Book("9780451474636", "The Forgotten Room",
            "A captivating historical mystery that intertwines the lives of three women across different generations, each connected by a grand mansion in New York and a hidden secret that spans decades.",
            384, Arrays.asList("Karen White", "Beatriz Williams", "Lauren Willig")));
    }

    // Retrieves a list of all books in the in-memory collection.
    @Override
    public List<Book> list() {
        return this.books;
    }

    // Finds and returns a book by its ISBN.
    @Override
    public Book find(String key) {
        for (Book book : this.books) {
            if (book.getIsbn().equals(key)) {
                return book;
            }
        }
        return new Book();
    }
}
