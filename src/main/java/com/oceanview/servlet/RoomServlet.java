package com.oceanview.servlet;

import com.oceanview.model.Room;
import com.oceanview.service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

// Servlet for room operations
@WebServlet("/api/rooms/*")
public class RoomServlet extends BaseServlet {
    private RoomService roomService;

    public RoomServlet() {
        this.roomService = new RoomService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        String pathInfo = request.getPathInfo();
        String available = request.getParameter("available");

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                if (available != null && available.equals("true")) {
                    List<Room> rooms = roomService.findAvailableRooms();
                    sendSuccess(response, "Available rooms retrieved", rooms);
                } else {
                    List<Room> rooms = roomService.getAllRooms();
                    sendSuccess(response, "Rooms retrieved successfully", rooms);
                }
            } else {
                String[] splits = pathInfo.split("/");
                if (splits.length == 2) {
                    int roomId = Integer.parseInt(splits[1]);
                    Room room = roomService.getRoomById(roomId);
                    if (room != null) {
                        sendSuccess(response, "Room found", room);
                    } else {
                        sendError(response, "Room not found");
                    }
                }
            }
        } catch (Exception e) {
            sendError(response, "Error retrieving rooms: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        try {
            BufferedReader reader = request.getReader();
            Room room = gson.fromJson(reader, Room.class);

            boolean success = roomService.addRoom(room);
            if (success) {
                sendSuccess(response, "Room added successfully", room);
            } else {
                sendError(response, "Failed to add room");
            }
        } catch (Exception e) {
            sendError(response, "Error adding room: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        try {
            BufferedReader reader = request.getReader();
            Room room = gson.fromJson(reader, Room.class);

            boolean success = roomService.updateRoom(room);
            if (success) {
                sendSuccess(response, "Room updated successfully", room);
            } else {
                sendError(response, "Failed to update room");
            }
        } catch (Exception e) {
            sendError(response, "Error updating room: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        String pathInfo = request.getPathInfo();

        try {
            String[] splits = pathInfo.split("/");
            if (splits.length == 2) {
                int roomId = Integer.parseInt(splits[1]);
                boolean success = roomService.deleteRoom(roomId);
                if (success) {
                    sendSuccess(response, "Room deleted successfully");
                } else {
                    sendError(response, "Failed to delete room");
                }
            }
        } catch (Exception e) {
            sendError(response, "Error deleting room: " + e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
