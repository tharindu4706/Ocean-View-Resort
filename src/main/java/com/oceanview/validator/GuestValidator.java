package com.oceanview.validator;

import com.oceanview.model.Guest;
import com.oceanview.util.StringUtil;

// Validator for Guest objects
public class GuestValidator implements Validator<Guest> {
    private String errorMessage;

    @Override
    public boolean validate(Guest guest) {
        if (guest == null) {
            errorMessage = "Guest cannot be null";
            return false;
        }
        if (StringUtil.isEmpty(guest.getGuestName())) {
            errorMessage = "Guest name cannot be empty";
            return false;
        }
        if (StringUtil.isEmpty(guest.getContactNumber())) {
            errorMessage = "Contact number cannot be empty";
            return false;
        }
        if (StringUtil.isEmpty(guest.getAddress())) {
            errorMessage = "Address cannot be empty";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
