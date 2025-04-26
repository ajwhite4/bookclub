/**
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 * 
 *  This is the REST controller for handling wishlist-related API endpoints
 *  within the BookClub web application.
 *  It defines request mappings for retrieving all wishlist items and finding
 *  a specific item by its ID.
 */
package com.bookclub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.service.impl.MongoWishlistDao;

// Designate this class as a REST controller
@RestController
// Set the base URL and specify that responses are produced in JSON format
@RequestMapping(path = "/api/wishlist", produces = "application/json")
// Allow cross-origin requests from any domain
@CrossOrigin(origins = "*")
public class WishlistRestController {

    // DAO for interacting with the wishlist collection
    WishlistDao wishlistDao = new MongoWishlistDao();

    /**
     * Setter method for injecting the WishlistDao dependency.
     * @param wishlistDao the DAO object for wishlist operations
     */
    @Autowired
    private void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    /**
     * Handles GET requests to retrieve all wishlist items.
     * @return a list of all wishlist items
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<WishlistItem> showWishlist() {
        return wishlistDao.list();
    }

    /**
     * Handles GET requests to retrieve a wishlist item by its ID.
     * @param id the unique identifier of the wishlist item
     * @return the wishlist item corresponding to the given ID
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public WishlistItem findById(@PathVariable String id) {
        return wishlistDao.find(id);
    }
}
