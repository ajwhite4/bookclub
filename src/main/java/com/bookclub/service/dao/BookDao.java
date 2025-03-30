/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.service.dao;

import com.bookclub.model.Book;
import com.bookclub.service.GenericDao;

/**
 * DAO interface for managing book data.
 */
public interface BookDao extends GenericDao<Book, String> {

}
