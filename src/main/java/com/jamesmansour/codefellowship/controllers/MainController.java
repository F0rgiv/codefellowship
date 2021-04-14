package com.jamesmansour.codefellowship.controllers;

import com.jamesmansour.codefellowship.models.ApplicationUser;
import com.jamesmansour.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String Landing(HttpServletRequest request, Model m) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) m.addAttribute("user", principal.getName());
        return "landing.html";
    }
}
