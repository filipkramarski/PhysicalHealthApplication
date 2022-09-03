package com.example.physicalhealthapplication.Controller;

import com.example.physicalhealthapplication.Domain.Post;
import com.example.physicalhealthapplication.Domain.User;
import com.example.physicalhealthapplication.Service.PostService;
import com.example.physicalhealthapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.security.Principal;
import java.util.Optional;
@Controller
@SessionAttributes("post")
//@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model, Principal principal) {


       String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // find post by id
        Optional<Post> optionalPost = this.postService.getById(id);
        // if post exist put it in model
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            // Check if current logged in user is owner and let view template know to take according actions
            if (authUsername.equals(post.getUser().getUsername())) {
                model.addAttribute("isOwner", true);
            }
            model.addAttribute("bodyContent", "post");
            return "master-template";
        } else {
            return "404";
        }
    }

    @GetMapping("/createNewPost")
    public String createNewPost(Model model, Principal principal) {


//        // get username of current logged in session user
        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // find user by username
        Optional<User> optionalBlogUser = this.userService.findByUsername(authUsername);
        // set user to post and put former in model
        if (optionalBlogUser.isPresent()) {
            Post post = new Post();
            post.setUser(optionalBlogUser.get());
            model.addAttribute("post", post);
            model.addAttribute("bodyContent", "postForm");
            return "master-template";
        } else {
            return "error";
        }
    }

    @PostMapping("/createNewPost")
    public String createNewPost(@ModelAttribute Post post, BindingResult bindingResult, SessionStatus sessionStatus) {
        System.err.println("POST post: " + post);
        if (bindingResult.hasErrors()) {
            System.err.println("Post did not validate");
            return "postForm";
        }
        // Save post if all good
        this.postService.save(post);
        System.err.println("SAVE post: " + post);
        sessionStatus.setComplete();
        return "redirect:/post/" + post.getId();
    }

    @GetMapping("editPost/{id}")
    public String editPost(@PathVariable Long id, Model model, Principal principal) {




//        // get username of current logged in session user
       String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // find post by id
        Optional<Post> optionalPost = this.postService.getById(id);
        // Check if current logged in user is an owner and so has the right for modifications to happen
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            // Check if current logged in user is owner
            if (authUsername.equals(post.getUser().getUsername())) {
                model.addAttribute("post", post);
                model.addAttribute("bodyContent", "postForm");
                System.err.println("EDIT post: " + post); // for testing debugging purposes
                return "master-template";
            } else {
                System.err.println("Current User has no permissions to edit anything on post by id: " + id); // for testing debugging purposes
                return "403";
            }
        } else {
            System.err.println("Could not find a post by id: " + id); // for testing debugging purposes
            return "error";
        }
    }

    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable Long id, Principal principal) {

        // Just curious  what if we get username from Principal instead of SecurityContext
        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        // the end of curiosity //

//        // get username of current logged in session user
//        String authUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // find post by id
        Optional<Post> optionalPost = this.postService.getById(id);
        // Check if current logged in user is an owner and so has the right for modifications to happen
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            // Check if current logged in user is owner
            if (authUsername.equals(post.getUser().getUsername())) {
                // if so then it is safe to remove post from database
                this.postService.delete(post);
                System.err.println("DELETED post: " + post); // for testing debugging purposes
                return "redirect:/";
            } else {
                System.err.println("Current User has no permissions to edit anything on post by id: " + id); // for testing debugging purposes
                return "403";
            }
        } else {
            System.err.println("Could not find a post by id: " + id); // for testing debugging purposes
            return "error";
        }
    }

}
