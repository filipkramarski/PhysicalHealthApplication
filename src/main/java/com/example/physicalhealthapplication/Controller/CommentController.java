package com.example.physicalhealthapplication.Controller;

import com.example.physicalhealthapplication.Domain.Comment;
import com.example.physicalhealthapplication.Domain.Post;
import com.example.physicalhealthapplication.Domain.User;
import com.example.physicalhealthapplication.Service.CommentService;
import com.example.physicalhealthapplication.Service.PostService;
import com.example.physicalhealthapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.security.Principal;
import java.util.Optional;

@Controller
@SessionAttributes("comment")
public class CommentController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public CommentController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/comment/{id}")
    public String showComment(@PathVariable Long id, Model model, Principal principal) {

        // Just curious  what if we get username from Principal instead of SecurityContext
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        // the end of curiosity //

//        // get username of current logged in session user
//        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        // find user by username
        Optional<User> optionalBlogUser = this.userService.findByUsername(authUsername);
        // find post by id
        Optional<Post> postOptional = this.postService.getById(id);
        // if both optionals is present set user and post to a new comment and put former in the model
        if (postOptional.isPresent() && optionalBlogUser.isPresent()) {
            Comment comment = new Comment();
            comment.setPost(postOptional.get());
            comment.setUser(optionalBlogUser.get());
            model.addAttribute("comment", comment);
            model.addAttribute("bodyContent", "commentForm");
            System.err.println("GET comment/{id}: " + comment + "/" + id); // for testing debugging purposes
            return "master-template";
        } else {
            System.err.println("Could not find a post by id: " + id + " or user by logged in username: " + authUsername); // for testing debugging purposes
            return "error";
        }
    }

    @PostMapping("/comment")
    public String validateComment(@ModelAttribute Comment comment, BindingResult bindingResult, SessionStatus sessionStatus) {
        System.err.println("POST comment: " + comment); // for testing debugging purposes
        if (bindingResult.hasErrors()) {
            System.err.println("Comment did not validate");
            return "commentForm";
        } else {
            this.commentService.save(comment);
            System.err.println("SAVE comment: " + comment); // for testing debugging purposes
            sessionStatus.setComplete();
            return "redirect:/post/" + comment.getPost().getId();
        }
    }

}
