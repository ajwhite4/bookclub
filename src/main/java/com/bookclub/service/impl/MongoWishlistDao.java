/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.service.impl;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MongoDB implementation of the WishlistDao interface.
 */
@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao {

    // Injects MongoTemplate for interacting with the MongoDB database
    @Autowired
    private MongoTemplate mongoTemplate;

    // Returns all wishlist items from the database.
    @Override
    public List<WishlistItem> list() {
        return mongoTemplate.findAll(WishlistItem.class);
    }

    // Saves a new wishlist item to the database.
    @Override
    public void add(WishlistItem entity) {
        mongoTemplate.save(entity);
    }

    // Finds a wishlist item in the database by id.
    @Override
    public WishlistItem find(String id) {
        return mongoTemplate.findById(id, WishlistItem.class);
    }

    @Override
    public void update(WishlistItem entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean remove(WishlistItem entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
}