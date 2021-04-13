package com.jamesmansour.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String Landing() {
        return "landing.html";
    }

    @GetMapping("/user")
    public RedirectView getUser(HttpServletRequest request){
        System.out.println("here we are :)");
        Principal principal = request.getUserPrincipal();
        System.out.println(principal);
        return new RedirectView("/users/{id}");
    }

    @GetMapping("/users/{id}")
    public String UserDetail() {
        return "user-details.html";
    }
}
