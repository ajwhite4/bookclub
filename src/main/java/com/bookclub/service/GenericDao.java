/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.service;

import java.util.List;

/**
 * Generic DAO interface for managing data entities.
 * @param <E> The entity type.
 * @param <K> The key type.
 */
public interface GenericDao<E, K> {
    List<E> list(); // Return a list of objects of type E.
    E find(K key); // Return an object of type E by type K value.
}
