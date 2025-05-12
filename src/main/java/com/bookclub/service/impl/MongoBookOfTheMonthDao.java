/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.service.impl;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MongoDB implementation of the BookOfTheMonthDao interface.
 */
@Repository("bookOfTheMonthDao")
public class MongoBookOfTheMonthDao implements BookOfTheMonthDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    // Adds a new book of the month
    @Override
    public void add(BookOfTheMonth entity) {
        mongoTemplate.save(entity);
    }

    // Removes a book of the month by ID
    @Override
    public boolean remove(String key) {
        Query query = new Query();
        
        query.addCriteria(Criteria.where("id").is(key));
        
        mongoTemplate.remove(query, BookOfTheMonth.class);
        
        return true;
    }

    // Lists all books or filters by month
    @Override
    public List<BookOfTheMonth> list(String key) {
        int month = Integer.parseInt(key);
        
        System.out.println("Month: " + month);

        if (month == 999) {
            return mongoTemplate.findAll(BookOfTheMonth.class);
        }

        Query query = new Query();
        
        query.addCriteria(Criteria.where("month").is(month));
        
        return mongoTemplate.find(query, BookOfTheMonth.class);
    }

    @Override
    public void update(BookOfTheMonth entity) {
        throw new UnsupportedOperationException("Update not supported.");
    }

    @Override
    public BookOfTheMonth find(String key) {
        throw new UnsupportedOperationException("Find not supported.");
    }
}
