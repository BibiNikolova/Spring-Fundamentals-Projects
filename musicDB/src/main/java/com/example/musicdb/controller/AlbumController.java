package com.example.musicdb.controller;

import com.example.musicdb.model.dto.CreateAlbumDTO;
import com.example.musicdb.service.AlbumService;
import com.example.musicdb.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AlbumController {

    private final AuthService authService;

    private  final AlbumService albumService;

    public AlbumController(AuthService authService, AlbumService albumService) {
        this.authService = authService;
        this.albumService = albumService;
    }

    @ModelAttribute("createAlbumDTO")
    public CreateAlbumDTO initCreateAlbumDTO(){
        return new CreateAlbumDTO();
    }

    @GetMapping("/albums/add")
    public String albums() {

        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "add-album";
    }

    @PostMapping("/albums/add")
    public String posts(@Valid CreateAlbumDTO createAlbumDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if(bindingResult.hasErrors() || !this.albumService.create(createAlbumDTO)){
            redirectAttributes.addFlashAttribute("createAlbumDTO", createAlbumDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createAlbumDTO", bindingResult);

            return "redirect:/albums/add";
        }
        return "redirect:/home";
    }
}
