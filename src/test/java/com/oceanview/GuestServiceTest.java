package com.oceanview;

import com.oceanview.service.GuestService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// Test class for GuestService
public class GuestServiceTest {
    private GuestService guestService;

    @Before
    public void setUp() {
        guestService = new GuestService();
    }

    @Test
    public void testGuestServiceNotNull() {
        assertNotNull("GuestService should not be null", guestService);
    }

    @Test
    public void testGetAllGuests() {
        assertNotNull("getAllGuests should not return null", guestService.getAllGuests());
    }
}
