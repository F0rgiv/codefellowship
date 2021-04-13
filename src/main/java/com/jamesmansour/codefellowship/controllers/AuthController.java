package com.jamesmansour.codefellowship.controllers;

import com.jamesmansour.codefellowship.models.ApplicationUser;
import com.jamesmansour.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/signup")
    public String showSingUp(){
        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password){
        ApplicationUser user = new ApplicationUser();
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        applicationUserRepository.save(user);
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login.html";
    }
}
