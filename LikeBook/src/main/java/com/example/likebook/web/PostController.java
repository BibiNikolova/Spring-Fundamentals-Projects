package com.example.likebook.web;

import com.example.likebook.model.dto.CreatePostDTO;
import com.example.likebook.service.AuthService;
import com.example.likebook.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostController {

    private final AuthService authService;

    private  final PostService postService;

    public PostController(AuthService authService, PostService postService) {
        this.authService = authService;
        this.postService = postService;
    }

    @ModelAttribute("createPostDTO")
    public CreatePostDTO initCreatePostDTO(){
        return new CreatePostDTO();
    }

    @GetMapping("/posts/add")
    public String posts() {

        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "post-add";
    }

    @PostMapping("/posts/add")
    public String posts(@Valid CreatePostDTO createPostDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if(bindingResult.hasErrors() || !this.postService.create(createPostDTO)){
            redirectAttributes.addFlashAttribute("createPostDTO", createPostDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createPostDTO", bindingResult);

            return "redirect:/posts/add";
        }
        return "redirect:/home";
    }
}
