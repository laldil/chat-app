package kz.edu.astanait.dashboard.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

public class SecurityUtils {
    public static Authentication getAuthInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getCurrentUsername() {
        var authInfo = getAuthInfo();
        return (String) authInfo.getPrincipal();
    }

    public static Long getCurrentId() {
        Authentication authInfo = getAuthInfo();
        return (Long) ((Map<String, Object>) authInfo.getDetails()).get("id");
    }
}
