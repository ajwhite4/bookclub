/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

/**
 * A simple in-memory implementation of the WishlistDao interface.
 * This class maintains a static list of wishlist items and provides methods to retrieve them.
 */
public class MemWishlistDao implements WishlistDao {

    // List to store wishlist item objects in memory
    private List<WishlistItem> wishlist;

    // Constructor to initialize the wishlist with predefined books.
    public MemWishlistDao() {
        this.wishlist = new ArrayList<>();
        wishlist.add(new WishlistItem("9781501192272", "The Talisman"));
        wishlist.add(new WishlistItem("9780425269404", "Patriot Games"));
    }

    // Retrieves a list of all wishlist items in the in-memory collection.
    @Override
    public List<WishlistItem> list() {
        return this.wishlist;
    }

    // Finds and returns a wishlist item by its ISBN.
    @Override
    public WishlistItem find(String key) {
        for (WishlistItem wishlist : this.wishlist) {
            if (wishlist.getIsbn().equals(key)) {
                return wishlist;
            }
        }
        return new WishlistItem();
    }
}
