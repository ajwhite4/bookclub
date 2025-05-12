/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.web;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Admin-only controller for managing Book of the Month entries.
 */
@Controller
@RequestMapping("/monthly-books")
public class AdminController {

    @Autowired
    private BookOfTheMonthDao bookOfTheMonthDao;

    /**
     * Shows all current BookOfTheMonth entries.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showBookOfTheMonth(Model model) {
        model.addAttribute("books", bookOfTheMonthDao.list("999"));
        return "monthly-books/list";
    }

    /**
     * Loads the form to add a new BookOfTheMonth entry.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/new")
    public String bookOfTheMonthForm(Model model) {
        model.addAttribute("book", new BookOfTheMonth());
        model.addAttribute("months", getMonths());
        return "monthly-books/new";
    }

    /**
     * Processes the form submission and adds the entry to MongoDB.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addBookOfTheMonth(@Valid BookOfTheMonth bookOfTheMonth, BindingResult bindingResult, Model model) {
        model.addAttribute("months", getMonths());

        if (bindingResult.hasErrors()) {
            return "monthly-books/new";
        }

        bookOfTheMonthDao.add(bookOfTheMonth);
        return "redirect:/monthly-books";
    }

    /**
     * Deletes a BookOfTheMonth entry by ID.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/remove/{id}")
    public String removeBookOfTheMonth(@PathVariable String id) {
        bookOfTheMonthDao.remove(id);
        return "redirect:/monthly-books";
    }

    /**
     * Supplies month options for the dropdown.
     */
    @ModelAttribute("months")
    public Map<Integer, String> getMonths() {
        Map<Integer, String> months = new HashMap<>();
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");
        return months;
    }
}
