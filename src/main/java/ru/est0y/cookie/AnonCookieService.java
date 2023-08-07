package ru.est0y.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import java.util.Arrays;
import java.util.UUID;

@Service
public class AnonCookieService {
    public boolean isExists(HttpServletRequest request) {
        if (request.getCookies() == null) return false;
        return Arrays.stream(request.getCookies()).anyMatch(c -> c.getName().equals("anon_id"));
    }

    public void createCookie(HttpServletResponse response) {
        var cookie = new Cookie("anon_id", UUID.randomUUID().toString());
        cookie.setMaxAge(10000000);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public String getAnonId(HttpServletRequest request) {
        return WebUtils.getCookie(request, "anon_id").getValue();
    }
}
