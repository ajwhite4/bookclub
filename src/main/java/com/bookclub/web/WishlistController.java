/**
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 * 
 * This is the controller for handling wishlist-related navigation and actions
 * within the BookClub web application.
 * It defines request mappings for viewing the wishlist, displaying the form 
 * to add a new wishlist item, and processing form submissions.
 */
package com.bookclub.web;

import com.bookclub.service.impl.MemWishlistDao;
import com.bookclub.model.WishlistItem;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Designate this class as a web controller
@Controller
// Set the base URL for the controller
@RequestMapping("/wishlist")
public class WishlistController {

    /**
     * This method runs when someone visits the "/wishlist" URL.
     * It retrieves the current wishlist and passes it to the list view.
     * @param model object for passing data to the view
     * @return the "wishlist/list" page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showWishlist(Model model) {
        MemWishlistDao wishlistDao = new MemWishlistDao();
        List<WishlistItem> wishlist = wishlistDao.list();
        model.addAttribute("wishlist", wishlist);
        return "wishlist/list";
    }

    /**
     * This method runs when someone visits "/wishlist/new".
     * It prepares the form for creating a new wishlist item.
     * @param model object for passing data to the view
     * @return the "wishlist/new" page
     */
    @RequestMapping(method = RequestMethod.GET, path = "/new")
    public String wishlistForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }

    /**
     * This method runs when someone submits the new wishlist item form.
     * It validates the submitted data and returns to the form if errors are found.
     * If validation passes, it redirects back to the wishlist list page.
     * @param wishlistItem the item submitted by the user
     * @param bindingResult stores validation errors
     * @return either the form with error messages or a redirect to "/wishlist"
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult) {
        System.out.println(wishlistItem.toString());

        System.out.println(bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        return "redirect:/wishlist";
    }
}
