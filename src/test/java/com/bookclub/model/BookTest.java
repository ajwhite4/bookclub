/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */

package com.bookclub.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Book class.
 */
public class BookTest {

    @Test
    public void testFullConstructor() {
        Book book = new Book("9781234567890", "Clean Code", "A guide to writing clean Java code.",
                "https://example.com/clean-code", 464);

        assertEquals("9781234567890", book.getIsbn());
        assertEquals("Clean Code", book.getTitle());
        assertEquals("A guide to writing clean Java code.", book.getDescription());
        assertEquals("https://example.com/clean-code", book.getInfoUrl());
        assertEquals(464, book.getNumOfPages());
    }

    @Test
    public void testSettersAndToString() {
        Book book = new Book();
        book.setIsbn("1112223334445");
        book.setTitle("Refactoring");
        book.setDescription("Improving the design of existing code.");
        book.setInfoUrl("https://example.com/refactoring");
        book.setNumOfPages(350);

        assertTrue(book.toString().contains("Refactoring"));
        assertEquals("1112223334445", book.getIsbn());
        assertEquals(350, book.getNumOfPages());
    }
}
