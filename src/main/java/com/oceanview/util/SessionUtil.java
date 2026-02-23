package com.oceanview.util;

import javax.servlet.http.HttpSession;

// Utility class for session operations
public class SessionUtil {

    // Get attribute from session
    public static Object getAttribute(HttpSession session, String key) {
        if (session != null) {
            return session.getAttribute(key);
        }
        return null;
    }

    // Set attribute in session
    public static void setAttribute(HttpSession session, String key, Object value) {
        if (session != null) {
            session.setAttribute(key, value);
        }
    }

    // Remove attribute from session
    public static void removeAttribute(HttpSession session, String key) {
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    // Check if user is logged in
    public static boolean isLoggedIn(HttpSession session) {
        if (session != null) {
            Object userId = session.getAttribute("userId");
            return userId != null;
        }
        return false;
    }

    // Get logged in user ID
    public static Integer getUserId(HttpSession session) {
        if (session != null) {
            Object userId = session.getAttribute("userId");
            if (userId != null) {
                return (Integer) userId;
            }
        }
        return null;
    }
}
