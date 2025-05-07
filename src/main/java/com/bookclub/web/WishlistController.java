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

import com.bookclub.service.impl.MongoWishlistDao;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.model.WishlistItem;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Designate this class as a web controller
@Controller
// Set the base URL for the controller
@RequestMapping("/wishlist")
public class WishlistController {

    WishlistDao wishlistDao = new MongoWishlistDao();

    @Autowired
    private void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    /**
     * This method runs when someone visits the "/wishlist" URL.
     * It retrieves the current wishlist and passes it to the list view.
     * @param model object for passing data to the view
     * @return the "wishlist/list" page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showWishlist(Model model) {
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
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication) {
        wishlistItem.setUsername(authentication.getName());

        System.out.println(wishlistItem.toString());
        System.out.println(bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        // Add the record to MongoDB
        wishlistDao.add(wishlistItem);

        return "redirect:/wishlist";
    }

    /**
     * Displays a single wishlist item.
     * @param id the unique MongoDB ID of the wishlist item
     * @param model used to pass the retrieved item to the view
     * @return the detailed wishlist item view page
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showWishlistItem(@PathVariable String id, Model model) {
        WishlistItem wishlistItem = wishlistDao.find(id);

        model.addAttribute("wishlistItem", wishlistItem);

        return "wishlist/view";
    }

    /**
     * Updates an existing wishlist item based on user input.
     * Validates the input before saving changes to MongoDB.
     * @param wishlistItem the updated wishlist item object
     * @param bindingResult result of form validation
     * @param authentication current logged-in user
     * @return redirect to wishlist list or reloads view form if errors exist
     */
    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public String updateWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication) {
        wishlistItem.setUsername(authentication.getName());

        if (bindingResult.hasErrors()) {
            return "wishlist/view";
        }

        wishlistDao.update(wishlistItem);

        return "redirect:/wishlist";
    }

    /**
     * Removes a wishlist item by its ID.
     * @param id the MongoDB ID of the item to be removed
     * @return redirect to the main wishlist list view
     */
    @RequestMapping(method = RequestMethod.GET, path = "/remove/{id}")
    public String removeWishlistItem(@PathVariable String id) {
        wishlistDao.remove(id);

        return "redirect:/wishlist";
    }
}
