package com.jamesmansour.codefellowship.controllers;

import com.jamesmansour.codefellowship.models.ApplicationUser;
import com.jamesmansour.codefellowship.models.Post;
import com.jamesmansour.codefellowship.repositories.ApplicationUserRepository;
import com.jamesmansour.codefellowship.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/addPost")
    public RedirectView addPost(String body, Long application_user_id, HttpServletRequest request){
        ApplicationUser user = applicationUserRepository.findByUsername(request.getUserPrincipal().getName());
        //catch bad actors
        if (!user.getId().equals(application_user_id)) return new RedirectView("/unothorized");

        Post post = new Post(body, user);
        postRepository.save(post);
        return new RedirectView("/users/" + application_user_id);
    }

}
