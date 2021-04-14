package com.jamesmansour.codefellowship.controllers;

import com.jamesmansour.codefellowship.models.ApplicationUser;
import com.jamesmansour.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/myProfile")
    public RedirectView getUser(HttpServletRequest request){
        ApplicationUser user = applicationUserRepository.findByUsername(request.getUserPrincipal().getName());
        return new RedirectView("/users/" + user.getId());
    }

    @GetMapping("/users/{id}")
    public String UserDetail(@PathVariable Long id, Model m, HttpServletRequest request) {
        m.addAttribute("user", request.getUserPrincipal().getName());
        ApplicationUser subject = applicationUserRepository.getOne(id);
        m.addAttribute("subject", subject);
        return "user-details.html";
    }

    @GetMapping("/users")
    public String getUsers(Model m, HttpServletRequest request) {
        m.addAttribute("user", request.getUserPrincipal().getName());
        m.addAttribute("users", applicationUserRepository.findAll());
        return "users";
    }
}
