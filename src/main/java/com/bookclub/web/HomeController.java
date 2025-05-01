/**
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 * 
 *  This is the main controller for handling navigation within the BookClub web application.
 *  It defines request mappings for the home, about, and contact pages.
 */
package com.bookclub.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bookclub.BookclubApplication;
import com.bookclub.model.Book;
import com.bookclub.service.impl.RestBookDao;

// Designate this class as a web controller
@Controller
// Set the base URL for the controller
@RequestMapping("/")
public class HomeController {

    private final BookclubApplication bookclubApplication;

    HomeController(BookclubApplication bookclubApplication) {
        this.bookclubApplication = bookclubApplication;
    }
 
    /** 
     * This method runs when someone visits the home page ("/").
     * @param model object for passing data to view
     * @return the "index" page.
     */ 
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {
        RestBookDao bookDao = new RestBookDao();
        List<Book> books = bookDao.list();

        for (Book book : books) {
            System.out.println(book.toString());
        }

        model.addAttribute("books", books);
        return "index";
    }
 
    /** 
     * This method runs when someone goes to "/about".
     * @param model object for passing data to view
     * @return the "about" page.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/about")
    public String showAboutUs(Model model) {
        return "about";
    }
 
    /** 
     * This method runs when someone goes to "/contact".
     * @param model object for passing data to view
     * @return the "contact" page.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/contact")
    public String showContactUs(Model model) {
        return "contact";
    }

    /**
     * Handles GET requests to retrieve details of the monthly book by its ID.
     * @param id The unique identifier (ISBN) of the book to retrieve.
     * @param model The Model object used to pass attributes to the view.
     * @return The view for displaying the book details.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String getMonthlyBook(@PathVariable("id") String id, Model model) {
        String isbn = id;
        System.out.println(id);

        RestBookDao bookDao = new RestBookDao();
        Book book = bookDao.find(isbn);

        System.out.println(book.toString());

        model.addAttribute("book", book);
        return "monthly-books/view";
    }
}