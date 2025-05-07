/*
 *  Gold, P. (2025). CIS 530 Server-Side Development. Bellevue University.
 *  Modified by A. White 2025
 */
package com.bookclub.service;

import java.util.List;

/**
 * Generic CRUD DAO interface for managing data entities.
 * @param <E> The entity type.
 * @param <K> The key type.
 */
public interface GenericCrudDao<E, K> {
    void add(E entity);
    void update(E entity);
    boolean remove(K key);
    List<E> list(K key);
    E find(K key);
}