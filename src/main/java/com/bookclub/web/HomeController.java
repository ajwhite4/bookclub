/**
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 * 
 *  This is the main controller for handling navigation within the BookClub web application.
 *  It defines request mappings for the home, about, and contact pages.
 */
package com.bookclub.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bookclub.BookclubApplication;
import com.bookclub.model.Book;
import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.service.impl.MongoBookOfTheMonthDao;
import com.bookclub.service.impl.RestBookDao;

// Designate this class as a web controller
@Controller
// Set the base URL for the controller
@RequestMapping("/")
public class HomeController {

    private final BookclubApplication bookclubApplication;

    private BookOfTheMonthDao bookOfTheMonthDao = new MongoBookOfTheMonthDao();

    HomeController(BookclubApplication bookclubApplication) {
        this.bookclubApplication = bookclubApplication;
    }

    // Setter for BookOfTheMonthDao (for Spring to inject if needed)
    @Autowired
    public void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao) {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }
 
    /** 
     * This method runs when someone visits the home page ("/").
     * @param model object for passing data to view
     * @return the "index" page.
     */ 
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int calMonth = cal.get(Calendar.MONTH) + 1;
        
        RestBookDao bookDao = new RestBookDao();
        List<BookOfTheMonth> monthlyBooks = bookOfTheMonthDao.list(Integer.toString(calMonth));

        StringBuilder isbnBuilder = new StringBuilder();
        isbnBuilder.append("ISBN:");

        for (BookOfTheMonth monthlyBook : monthlyBooks) {
            isbnBuilder.append(monthlyBook.getIsbn()).append(",");
        }

        String isbnString = isbnBuilder.toString().substring(0, isbnBuilder.toString().length() - 1);

        List<Book> books = bookDao.list(isbnString);

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