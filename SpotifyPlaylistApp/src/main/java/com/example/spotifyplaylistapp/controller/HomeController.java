package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.PlaylistDTO;
import com.example.spotifyplaylistapp.model.dto.SongsByGenreDTO;
import com.example.spotifyplaylistapp.service.AuthService;
import com.example.spotifyplaylistapp.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final SongService songService;
    private final AuthService authService;

    @Autowired
    public HomeController(SongService songService, AuthService authService) {
        this.songService = songService;
        this.authService = authService;
    }

    @ModelAttribute("PlaylistDTO")
    public PlaylistDTO initPlaylistVisualForm() {
        return new PlaylistDTO();
    }
    @ModelAttribute("SongsByGenreDTO")
    public SongsByGenreDTO initByGenre() {
        return  new SongsByGenreDTO();
    }

    @GetMapping("/")
    public String loggedOutIndex() {

        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        model.addAttribute("songs", this.songService.getSongs());
        model.addAttribute("totalTime", this.songService.totalTimePlaylist());
        model.addAttribute("playlist", this.songService.getPlaylist());

        return "home";

    }

    @GetMapping("/home/add/{id}")
    public String addSongToPlaylist(@PathVariable Long id) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.songService.addSongToPlaylist(id);

        return "redirect:/home";
    }

    @GetMapping("/home/removeAll")
    public String removeAllPlaylist() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.songService.removeAllSongsFromPlaylist();

        return "redirect:/home";
    }

}
