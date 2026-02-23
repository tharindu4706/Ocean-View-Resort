package com.oceanview.servlet;

import com.oceanview.dto.LoginRequest;
import com.oceanview.model.User;
import com.oceanview.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

// Servlet for user login
@WebServlet("/api/login")
public class LoginServlet extends BaseServlet {
    private UserService userService;

    public LoginServlet() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        try {
            BufferedReader reader = request.getReader();
            LoginRequest loginRequest = gson.fromJson(reader, LoginRequest.class);

            User user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("username", user.getUsername());
                sendSuccess(response, "Login successful", user);
            } else {
                sendError(response, "Invalid username or password");
            }
        } catch (Exception e) {
            sendError(response, "Error during login: " + e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
