package com.oceanview.servlet;

import com.oceanview.model.Guest;
import com.oceanview.service.GuestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

// Servlet for guest operations
@WebServlet("/api/guests/*")
public class GuestServlet extends BaseServlet {
    private GuestService guestService;

    public GuestServlet() {
        this.guestService = new GuestService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Guest> guests = guestService.getAllGuests();
                sendSuccess(response, "Guests retrieved successfully", guests);
            } else {
                String[] splits = pathInfo.split("/");
                if (splits.length == 2) {
                    int guestId = Integer.parseInt(splits[1]);
                    Guest guest = guestService.getGuestById(guestId);
                    if (guest != null) {
                        sendSuccess(response, "Guest found", guest);
                    } else {
                        sendError(response, "Guest not found");
                    }
                }
            }
        } catch (Exception e) {
            sendError(response, "Error retrieving guests: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        try {
            BufferedReader reader = request.getReader();
            Guest guest = gson.fromJson(reader, Guest.class);

            boolean success = guestService.addGuest(guest);
            if (success) {
                sendSuccess(response, "Guest added successfully", guest);
            } else {
                sendError(response, "Failed to add guest");
            }
        } catch (Exception e) {
            sendError(response, "Error adding guest: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        try {
            BufferedReader reader = request.getReader();
            Guest guest = gson.fromJson(reader, Guest.class);

            boolean success = guestService.updateGuest(guest);
            if (success) {
                sendSuccess(response, "Guest updated successfully", guest);
            } else {
                sendError(response, "Failed to update guest");
            }
        } catch (Exception e) {
            sendError(response, "Error updating guest: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        String pathInfo = request.getPathInfo();

        try {
            String[] splits = pathInfo.split("/");
            if (splits.length == 2) {
                int guestId = Integer.parseInt(splits[1]);
                boolean success = guestService.deleteGuest(guestId);
                if (success) {
                    sendSuccess(response, "Guest deleted successfully");
                } else {
                    sendError(response, "Failed to delete guest");
                }
            }
        } catch (Exception e) {
            sendError(response, "Error deleting guest: " + e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
