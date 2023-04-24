package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.CreateSongDTO;
import com.example.spotifyplaylistapp.service.AuthService;
import com.example.spotifyplaylistapp.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SongController {

    private final AuthService authService;

    private  final SongService songService;

    public SongController(AuthService authService, SongService songService) {
        this.authService = authService;
        this.songService = songService;
    }

    @ModelAttribute("createSongDTO")
    public CreateSongDTO initCreateSongDTO(){
        return new CreateSongDTO();
    }

    @GetMapping("/songs/add")
    public String songs() {

        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "song-add";
    }

    @PostMapping("/songs/add")
    public String songs(@Valid CreateSongDTO createSongDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if(bindingResult.hasErrors() || !this.songService.create(createSongDTO)){
            redirectAttributes.addFlashAttribute("createSongDTO", createSongDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createSongDTO", bindingResult);

            return "redirect:/songs/add";
        }
        return "redirect:/home";
    }
}
