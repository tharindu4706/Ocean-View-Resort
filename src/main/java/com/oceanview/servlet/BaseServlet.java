package com.oceanview.servlet;

import com.google.gson.Gson;
import com.oceanview.dto.ApiResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// Base servlet with common methods (DRY Principle)
public class BaseServlet extends HttpServlet {
    protected Gson gson;

    public BaseServlet() {
        this.gson = new Gson();
    }

    // Send JSON response
    protected void sendJsonResponse(HttpServletResponse response, Object data) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(data));
        out.flush();
    }

    // Send success response
    protected void sendSuccess(HttpServletResponse response, String message, Object data) throws IOException {
        ApiResponse apiResponse = new ApiResponse(true, message, data);
        sendJsonResponse(response, apiResponse);
    }

    // Send success response without data
    protected void sendSuccess(HttpServletResponse response, String message) throws IOException {
        ApiResponse apiResponse = new ApiResponse(true, message);
        sendJsonResponse(response, apiResponse);
    }

    // Send error response
    protected void sendError(HttpServletResponse response, String message) throws IOException {
        ApiResponse apiResponse = new ApiResponse(false, message);
        sendJsonResponse(response, apiResponse);
    }

    // Set CORS headers
    protected void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
