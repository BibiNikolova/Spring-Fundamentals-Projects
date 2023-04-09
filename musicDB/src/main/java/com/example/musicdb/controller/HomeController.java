package com.example.musicdb.controller;

import com.example.musicdb.model.dto.AlbumViewDTO;
import com.example.musicdb.service.AlbumService;
import com.example.musicdb.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final AlbumService albumService;
    private final AuthService authService;

    public HomeController(AlbumService albumService, AuthService authService) {
        this.albumService = albumService;
        this.authService = authService;
    }


    @ModelAttribute("albumViewDTO")
    public AlbumViewDTO initMyAlbumVisualForm() {
        return new AlbumViewDTO();
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

        model.addAttribute("albums", this.albumService.getAlbums());
        model.addAttribute("copies", this.albumService.numOfCopies());

        return "home";
    }

    @GetMapping("/home/delete/{albumId}")
    public String removeAlbum(@PathVariable Long albumId) {


        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.albumService.removeAlbum(albumId);

        return "redirect:/home";
    }
}

