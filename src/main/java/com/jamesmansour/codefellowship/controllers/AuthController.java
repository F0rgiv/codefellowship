package com.jamesmansour.codefellowship.controllers;

import com.jamesmansour.codefellowship.models.ApplicationUser;
import com.jamesmansour.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/signup")
    public String showSingUp(){
        return "signup.html";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, HttpServletRequest request){
        ApplicationUser user = new ApplicationUser();
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        applicationUserRepository.save(user);

//      ================ User Created =============

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

//      ================ User Signed in =============

        return new RedirectView("/myProfile");
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login.html";
    }
}
