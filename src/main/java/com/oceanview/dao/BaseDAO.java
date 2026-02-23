package com.oceanview.dao;

import java.util.List;

// Interface for basic database operations (Interface Segregation Principle)
public interface BaseDAO<T> {
    // Add new record
    boolean add(T item);

    // Update existing record
    boolean update(T item);

    // Delete record by id
    boolean delete(int id);

    // Get single record by id
    T getById(int id);

    // Get all records
    List<T> getAll();
}
