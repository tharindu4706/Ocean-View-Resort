package com.oceanview.servlet;

import com.oceanview.dto.BillDTO;
import com.oceanview.model.Guest;
import com.oceanview.model.Reservation;
import com.oceanview.model.Room;
import com.oceanview.service.BillCalculator;
import com.oceanview.service.GuestService;
import com.oceanview.service.ReservationService;
import com.oceanview.service.RoomService;
import com.oceanview.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Servlet for bill calculation and display
@WebServlet("/api/bill/*")
public class BillServlet extends BaseServlet {
    private ReservationService reservationService;
    private GuestService guestService;
    private RoomService roomService;
    private BillCalculator billCalculator;

    public BillServlet() {
        this.reservationService = new ReservationService();
        this.guestService = new GuestService();
        this.roomService = new RoomService();
        this.billCalculator = new BillCalculator();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);

        String pathInfo = request.getPathInfo();

        try {
            String[] splits = pathInfo.split("/");
            if (splits.length == 2) {
                int reservationId = Integer.parseInt(splits[1]);

                Reservation reservation = reservationService.getReservationById(reservationId);
                if (reservation == null) {
                    sendError(response, "Reservation not found");
                    return;
                }

                Guest guest = guestService.getGuestById(reservation.getGuestId());
                Room room = roomService.getRoomById(reservation.getRoomId());

                BillDTO bill = new BillDTO();
                bill.setReservationNumber(reservation.getReservationNumber());
                bill.setGuestName(guest != null ? guest.getGuestName() : "Unknown");
                bill.setRoomNumber(room != null ? room.getRoomNumber() : "Unknown");
                bill.setCheckInDate(DateUtil.dateToString(reservation.getCheckInDate()));
                bill.setCheckOutDate(DateUtil.dateToString(reservation.getCheckOutDate()));

                long nights = DateUtil.daysBetween(reservation.getCheckInDate(), reservation.getCheckOutDate());
                bill.setNumberOfNights((int) nights);
                bill.setPricePerNight(room != null ? room.getOvernightPrice() : 0);

                double subtotal = billCalculator.calculateBill(reservation);
                double tax = subtotal * 0.1;
                double total = subtotal + tax;

                bill.setSubtotal(subtotal);
                bill.setTax(tax);
                bill.setTotal(total);

                sendSuccess(response, "Bill retrieved successfully", bill);
            }
        } catch (Exception e) {
            sendError(response, "Error calculating bill: " + e.getMessage());
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCorsHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
