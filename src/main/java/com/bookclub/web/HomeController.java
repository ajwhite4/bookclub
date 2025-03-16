/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 * 
 *  This is the main controller for handling navigation within the BookClub web application.
 *  It defines request mappings for the home, about, and contact pages.
 */
 package com.bookclub.web;

 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;

 // Designate this class as a web controller
 @Controller
 // Set the base URL for the controller
 @RequestMapping("/")
 public class HomeController {
 
    /** 
     * This method runs when someone visits the home page ("/").
     * @param model object for passing data to view
     * @return the "index" page.
     */ 
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {
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
}