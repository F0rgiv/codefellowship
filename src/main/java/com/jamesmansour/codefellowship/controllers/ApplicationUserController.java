package com.jamesmansour.codefellowship.controllers;

import com.jamesmansour.codefellowship.models.ApplicationUser;
import com.jamesmansour.codefellowship.models.Post;
import com.jamesmansour.codefellowship.repositories.ApplicationUserRepository;
import com.jamesmansour.codefellowship.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ApplicationUserController {

    @Autowired ApplicationUserRepository applicationUserRepository;

    @GetMapping("/myProfile")
    public RedirectView getUser(HttpServletRequest request){
        ApplicationUser user = applicationUserRepository.findByUsername(request.getUserPrincipal().getName());
        return new RedirectView("/users/" + user.getId());
    }

    @GetMapping("/users/{id}")
    public String userDetail(@PathVariable Long id, Model m, HttpServletRequest request) {
        m.addAttribute("user", request.getUserPrincipal().getName());
        ApplicationUser subject = applicationUserRepository.findById(id).get();
        m.addAttribute("subject", subject);
        return "user-details";
    }

    @PostMapping("/follow")
    public RedirectView followDetail(Model m, HttpServletRequest request, Long toFollowId) {
        ApplicationUser user = applicationUserRepository.findByUsername(request.getUserPrincipal().getName());
        ApplicationUser subject = applicationUserRepository.findById(toFollowId).get();
        subject.getFollowers().add(user);
        applicationUserRepository.save(subject);
        return new RedirectView("/users/" + toFollowId);
    }

    @GetMapping("/users")
    public String getUsers(Model m, HttpServletRequest request) {
        m.addAttribute("user", request.getUserPrincipal().getName());
        m.addAttribute("users", applicationUserRepository.findAll());
        return "users";
    }

    @GetMapping("/feed")
    public String getFeed(Model m, HttpServletRequest request) {
        ApplicationUser user = applicationUserRepository.findByUsername(request.getUserPrincipal().getName());
        m.addAttribute("user", user.getUsername());
        List<Post> posts = new LinkedList<>();
        for (ApplicationUser person : user.getFollowing()) {
            posts.addAll(person.getPosts());
        }
        m.addAttribute("posts", posts);
        return "feed";
    }
}
