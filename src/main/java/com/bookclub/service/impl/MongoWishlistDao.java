/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.service.impl;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    public List<WishlistItem> list(String username) {
        Query query = new Query();

        query.addCriteria(Criteria.where("username").is(username));

        return mongoTemplate.find(query, WishlistItem.class);
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

    // Updates an existing wishlist item
    @Override
    public void update(WishlistItem entity) {
        WishlistItem wishlistItem = mongoTemplate.findById(entity.getId(), WishlistItem.class);

        if (wishlistItem != null) {
            wishlistItem.setIsbn(entity.getIsbn());
            wishlistItem.setTitle(entity.getTitle());
            wishlistItem.setUsername(entity.getUsername());

            mongoTemplate.save(wishlistItem);
        }
    }

    // Removes a wishlist item by ID
    @Override
    public boolean remove(String key) {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(key));

        mongoTemplate.remove(query, WishlistItem.class);

        return true;
    }
}