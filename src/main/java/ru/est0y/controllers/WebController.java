package ru.est0y.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.est0y.cookie.AnonCookieService;
import ru.est0y.repositories.GameRepository;

import java.security.Principal;

@AllArgsConstructor
@Controller
@Slf4j
public class WebController {
    private final AnonCookieService anonCookieService;
    private final GameRepository gameRepository;


    @GetMapping("/")
    public String mainPage(Model model) {
        return "mainPage";
    }

    @GetMapping("/room/{roomId}")
    public String roomPage(@PathVariable String roomId,
                           Model model,
                           HttpServletResponse response,
                           HttpServletRequest request) {
        if (!anonCookieService.isExists(request)) {
            anonCookieService.createCookie(response);
        }
        model.addAttribute("roomId", roomId);
        return "room";
        //return "roomWith3Seat";
    }


}
