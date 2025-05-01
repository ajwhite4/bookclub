/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * A RESTful implementation of the BookDao interface.
 * This class retrieves book data from the OpenLibrary API and maps it into Book objects.
 */
public class RestBookDao implements BookDao {

    // Default constructor
    public RestBookDao() {  }

    /**
     * Sends a GET request to the OpenLibrary API using provided ISBNs and parses the result.
     * @param isbnString A comma-separated list of ISBNs
     * @return Parsed JSON object from OpenLibrary
     */
    private Object getBooksDoc(String isbnString) {
        String openLibraryUrl = "https://openlibrary.org/api/books";

        // Create the RestTemplate object to make the HTTP request
        RestTemplate rest = new RestTemplate(); // call the RestTemplate object to invoke a third-party API call.

        // Create and configure request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        // Build the API URL with query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
                .queryParam("bibkeys", isbnString)
                .queryParam("format", "json")
                .queryParam("jscmd", "details");

        // Wrap headers into an HttpEntity for the request
        HttpEntity<?> entity = new HttpEntity<>(headers);

        // Send the GET request to OpenLibrary and store the response
        HttpEntity<String> response = rest.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        // Parse the JSON string response into an object
        String jsonBooklist = response.getBody();
        return Configuration.defaultConfiguration().jsonProvider().parse(jsonBooklist);
    }

    /**
     * Retrieves a list of books from OpenLibrary using hardcoded ISBNs.
     * Maps the response into a list of Book objects.
     * @return List of Book objects
     */
    @Override
    public List<Book> list() {
        // Hardcoded list of ISBNs to retrieve from OpenLibrary
        String isbnString = "ISBN:9780593099322,9780261102361,9780261102378,9780590302715,9780316769532";

        // Make API call and get parsed JSON document
        Object doc = getBooksDoc(isbnString);

        List<Book> books = new ArrayList<Book>();

        // Extract book data from JSON response
        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");

        // Add returned books to book list
        for (int index = 0; index < titles.size(); index++) {
            books.add(new Book(isbns.get(index), titles.get(index), infoUrls.get(index)));
        }

        return books;
    }

    /**
     * Finds a single book by ISBN using the OpenLibrary API.
     * Maps the API response into a Book object.
     * @param key ISBN of the book to find
     * @return Book object with populated fields
     */
    @Override
    public Book find(String key) {
        Object doc = getBooksDoc(key);

        // Extract fields from the JSON response
        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> subtitle = JsonPath.read(doc, "$..details.subtitle");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");
        List<Integer> pages = JsonPath.read(doc, "$..details.number_of_pages");

        // Safely extract first item from each field or assign a default
        String isbn = isbns.size() > 0 ? isbns.get(0) : "N/A";
        String title = titles.size() > 0 ? titles.get(0) : "N/A";
        String desc = subtitle.size() > 0 ? subtitle.get(0) : "N/A";
        String infoUrl = infoUrls.size() > 0 ? infoUrls.get(0) : "N/A";
        int numOfPages = pages.size() > 0 ? pages.get(0) : 0;

        // Construct and return a Book object
        Book book = new Book(isbn, title, desc, infoUrl, numOfPages);

        return book;
    }
}
