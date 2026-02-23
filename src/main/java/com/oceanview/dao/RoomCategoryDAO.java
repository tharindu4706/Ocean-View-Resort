package com.oceanview.dao;

import com.oceanview.model.RoomCategory;

// Interface for RoomCategory data operations
public interface RoomCategoryDAO extends BaseDAO<RoomCategory> {
    RoomCategory findByCategoryName(String categoryName);
}
