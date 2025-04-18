/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 * 
 *  This is the controller for handling login and logout actions within the BookClub web application.
 *  It defines request mappings for rendering the login page and manually logging out users.
 */
package com.bookclub.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {

    /** 
     * This method runs when someone goes to "/login".
     * It returns the login view.
     * @return the "login" page.
     */
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    /** 
     * This method runs when someone goes to "/logout".
     * It performs a manual logout and redirects the user to the login page.
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     * @return redirect to the login page with logout confirmation
     */
    @RequestMapping(path = "/logout",  method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
}