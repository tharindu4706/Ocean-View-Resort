package com.oceanview.servlet;

import com.oceanview.dto.ReservationRequest;
import com.oceanview.model.Reservation;
import com.oceanview.service.BillCalculator;
import com.oceanview.service.ReservationService;
import com.oceanview.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

// Servlet for reservation operations
@WebServlet("/api/reservations/*")
public class ReservationServlet extends BaseServlet {
    private ReservationService reservationService;
    private BillCalculator billCalculator;

    public ReservationServlet() {
        this.reservationService = new ReservationService();
        this.billCalculator = new BillCalculator();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Reservation> reservations = reservationService.getAllReservations();
                sendSuccess(response, "Reservations retrieved successfully", reservations);
            } else {
                String[] splits = pathInfo.split("/");
                if (splits.length == 2) {
                    int reservationId = Integer.parseInt(splits[1]);
                    Reservation reservation = reservationService.getReservationById(reservationId);
                    if (reservation != null) {
                        sendSuccess(response, "Reservation found", reservation);
                    } else {
                        sendError(response, "Reservation not found");
                    }
                }
            }
        } catch (Exception e) {
            sendError(response, "Error retrieving reservations: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        try {
            BufferedReader reader = request.getReader();
            ReservationRequest reservationRequest = gson.fromJson(reader, ReservationRequest.class);

            Reservation reservation = new Reservation();
            reservation.setGuestId(reservationRequest.getGuestId());
            reservation.setRoomId(reservationRequest.getRoomId());

            Date checkIn = DateUtil.stringToDate(reservationRequest.getCheckInDate());
            Date checkOut = DateUtil.stringToDate(reservationRequest.getCheckOutDate());

            reservation.setCheckInDate(checkIn);
            reservation.setCheckOutDate(checkOut);

            double totalAmount = billCalculator.calculateBill(reservation);
            reservation.setTotalAmount(totalAmount);

            boolean success = reservationService.createReservation(reservation);
            if (success) {
                sendSuccess(response, "Reservation created successfully", reservation);
            } else {
                sendError(response, "Failed to create reservation");
            }
        } catch (Exception e) {
            sendError(response, "Error creating reservation: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        try {
            BufferedReader reader = request.getReader();
            Reservation reservation = gson.fromJson(reader, Reservation.class);

            boolean success = reservationService.updateReservation(reservation);
            if (success) {
                sendSuccess(response, "Reservation updated successfully", reservation);
            } else {
                sendError(response, "Failed to update reservation");
            }
        } catch (Exception e) {
            sendError(response, "Error updating reservation: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        String pathInfo = request.getPathInfo();

        try {
            String[] splits = pathInfo.split("/");
            if (splits.length == 2) {
                int reservationId = Integer.parseInt(splits[1]);
                boolean success = reservationService.cancelReservation(reservationId);
                if (success) {
                    sendSuccess(response, "Reservation cancelled successfully");
                } else {
                    sendError(response, "Failed to cancel reservation");
                }
            }
        } catch (Exception e) {
            sendError(response, "Error cancelling reservation: " + e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
