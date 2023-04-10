package com.example.likebook.web;

import com.example.likebook.model.dto.PostViewDTO;
import com.example.likebook.service.AuthService;
import com.example.likebook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final PostService postService;
    private final AuthService authService;

    @Autowired
    public HomeController(PostService postService, AuthService authService) {
        this.postService = postService;
        this.authService = authService;

    }

    @ModelAttribute("postViewDTO")
    public PostViewDTO initMyPostVisualForm() {
        return new PostViewDTO();
    }


    @GetMapping("/")
    public String loggedOutIndex() {

        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String loggedInHome(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        model.addAttribute("posts", this.postService.getPosts());
        model.addAttribute("user", this.authService.getLoggedUserId());

        return "home";
    }

    @GetMapping("/home/like/{postId}")
    public String likePost(@PathVariable Long postId) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.postService.likePost(postId);

        return "redirect:/home";
    }

    @GetMapping("/home/remove/{postId}")
    public String removePost(@PathVariable Long postId) {


        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.postService.removePost(postId);

        return "redirect:/home";
    }
}

