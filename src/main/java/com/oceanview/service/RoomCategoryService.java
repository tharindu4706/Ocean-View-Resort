package com.oceanview.service;

import com.oceanview.dao.RoomCategoryDAO;
import com.oceanview.dao.RoomCategoryDAOImpl;
import com.oceanview.model.RoomCategory;

import java.util.List;

// Service class for room category business logic
public class RoomCategoryService {
    private RoomCategoryDAO categoryDAO;

    public RoomCategoryService() {
        this.categoryDAO = new RoomCategoryDAOImpl();
    }

    public boolean addCategory(RoomCategory category) {
        if (category == null || category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            return false;
        }
        return categoryDAO.add(category);
    }

    public boolean updateCategory(RoomCategory category) {
        if (category == null || category.getCategoryId() <= 0) {
            return false;
        }
        return categoryDAO.update(category);
    }

    public boolean deleteCategory(int categoryId) {
        if (categoryId <= 0) {
            return false;
        }
        return categoryDAO.delete(categoryId);
    }

    public RoomCategory getCategoryById(int categoryId) {
        return categoryDAO.getById(categoryId);
    }

    public List<RoomCategory> getAllCategories() {
        return categoryDAO.getAll();
    }

    public RoomCategory findByCategoryName(String categoryName) {
        return categoryDAO.findByCategoryName(categoryName);
    }
}
