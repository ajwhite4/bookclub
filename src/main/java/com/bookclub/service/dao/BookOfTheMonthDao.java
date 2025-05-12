/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.service.dao;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.GenericCrudDao;

/**
 * DAO interface for BookOfTheMonth operations.
 */
public interface BookOfTheMonthDao extends GenericCrudDao<BookOfTheMonth, String> {
}
