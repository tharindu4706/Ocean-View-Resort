package com.oceanview.servlet;

import com.oceanview.model.RoomCategory;
import com.oceanview.service.RoomCategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

// Servlet for room category operations
@WebServlet("/api/room-categories/*")
public class RoomCategoryServlet extends BaseServlet {
    private RoomCategoryService categoryService;

    public RoomCategoryServlet() {
        this.categoryService = new RoomCategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<RoomCategory> categories = categoryService.getAllCategories();
                sendSuccess(response, "Room categories retrieved successfully", categories);
            } else {
                String[] splits = pathInfo.split("/");
                if (splits.length == 2) {
                    int categoryId = Integer.parseInt(splits[1]);
                    RoomCategory category = categoryService.getCategoryById(categoryId);
                    if (category != null) {
                        sendSuccess(response, "Room category found", category);
                    } else {
                        sendError(response, "Room category not found");
                    }
                }
            }
        } catch (Exception e) {
            sendError(response, "Error retrieving room categories: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        try {
            BufferedReader reader = request.getReader();
            RoomCategory category = gson.fromJson(reader, RoomCategory.class);

            boolean success = categoryService.addCategory(category);
            if (success) {
                sendSuccess(response, "Room category added successfully", category);
            } else {
                sendError(response, "Failed to add room category");
            }
        } catch (Exception e) {
            sendError(response, "Error adding room category: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        try {
            BufferedReader reader = request.getReader();
            RoomCategory category = gson.fromJson(reader, RoomCategory.class);

            boolean success = categoryService.updateCategory(category);
            if (success) {
                sendSuccess(response, "Room category updated successfully", category);
            } else {
                sendError(response, "Failed to update room category");
            }
        } catch (Exception e) {
            sendError(response, "Error updating room category: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        String pathInfo = request.getPathInfo();

        try {
            String[] splits = pathInfo.split("/");
            if (splits.length == 2) {
                int categoryId = Integer.parseInt(splits[1]);
                boolean success = categoryService.deleteCategory(categoryId);
                if (success) {
                    sendSuccess(response, "Room category deleted successfully");
                } else {
                    sendError(response, "Failed to delete room category");
                }
            }
        } catch (Exception e) {
            sendError(response, "Error deleting room category: " + e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
