package com.oceanview.dao;

import com.oceanview.database.DatabaseConnection;
import com.oceanview.model.RoomCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Implementation of RoomCategoryDAO
public class RoomCategoryDAOImpl implements RoomCategoryDAO {
    private Connection connection;

    public RoomCategoryDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean add(RoomCategory category) {
        String sql = "INSERT INTO room_categories (category_name, capacity, day_use_price, day_use_hourly_rate, overnight_price, late_checkout_half_day, late_checkout_full_day) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getCapacity());
            statement.setDouble(3, category.getDayUsePrice());
            statement.setDouble(4, category.getDayUseHourlyRate());
            statement.setDouble(5, category.getOvernightPrice());
            statement.setDouble(6, category.getLateCheckoutHalfDay());
            statement.setDouble(7, category.getLateCheckoutFullDay());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error adding room category: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(RoomCategory category) {
        String sql = "UPDATE room_categories SET category_name=?, capacity=?, day_use_price=?, day_use_hourly_rate=?, overnight_price=?, late_checkout_half_day=?, late_checkout_full_day=? WHERE category_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getCapacity());
            statement.setDouble(3, category.getDayUsePrice());
            statement.setDouble(4, category.getDayUseHourlyRate());
            statement.setDouble(5, category.getOvernightPrice());
            statement.setDouble(6, category.getLateCheckoutHalfDay());
            statement.setDouble(7, category.getLateCheckoutFullDay());
            statement.setInt(8, category.getCategoryId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating room category: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM room_categories WHERE category_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting room category: " + e.getMessage());
            return false;
        }
    }

    @Override
    public RoomCategory getById(int id) {
        String sql = "SELECT * FROM room_categories WHERE category_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createCategoryFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error getting room category: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<RoomCategory> getAll() {
        List<RoomCategory> categories = new ArrayList<>();
        String sql = "SELECT * FROM room_categories ORDER BY category_name";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                categories.add(createCategoryFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all room categories: " + e.getMessage());
        }
        return categories;
    }

    @Override
    public RoomCategory findByCategoryName(String categoryName) {
        String sql = "SELECT * FROM room_categories WHERE category_name=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categoryName);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createCategoryFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error finding room category: " + e.getMessage());
        }
        return null;
    }

    // Helper method to create RoomCategory object from database result
    private RoomCategory createCategoryFromResult(ResultSet result) throws SQLException {
        RoomCategory category = new RoomCategory();
        category.setCategoryId(result.getInt("category_id"));
        category.setCategoryName(result.getString("category_name"));
        category.setCapacity(result.getInt("capacity"));
        category.setDayUsePrice(result.getDouble("day_use_price"));
        category.setDayUseHourlyRate(result.getDouble("day_use_hourly_rate"));
        category.setOvernightPrice(result.getDouble("overnight_price"));
        category.setLateCheckoutHalfDay(result.getDouble("late_checkout_half_day"));
        category.setLateCheckoutFullDay(result.getDouble("late_checkout_full_day"));
        return category;
    }
}
