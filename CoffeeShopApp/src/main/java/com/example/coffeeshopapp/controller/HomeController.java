package com.example.coffeeshopapp.controller;

import com.example.coffeeshopapp.service.AuthService;
import com.example.coffeeshopapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final OrderService orderService;
    private final AuthService authService;
//
//    @ModelAttribute("PlaylistDTO")
//    public PlaylistDTO initPlaylistVisualForm() {
//        return new PlaylistDTO();
//    }
//    @ModelAttribute("SongsByGenreDTO")
//    public SongsByGenreDTO initByGenre() {
//        return  new SongsByGenreDTO();
//    }
//
    @Autowired
    public HomeController(OrderService orderService, AuthService authService) {
        this.orderService = orderService;
        this.authService = authService;
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

//        model.addAttribute("songs", this.songService.getSongs());
//        model.addAttribute("totalTime", this.songService.totalTimePlaylist());
//        model.addAttribute("playlist", this.songService.getPlaylist());

        return "home";

    }

    @GetMapping("/orders/add/{id}")
    public String addOrder(@PathVariable Long id) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
//
//        this.orderService.addSongToPlaylist(id);
        return "redirect:/home";
    }
//
//    @GetMapping("/home/removeAll/{id}")
//    public String removeAllPlaylist(@PathVariable Long id) {
//
//        if (!this.authService.isLoggedIn()) {
//            return "redirect:/";
//        }
//
//        this.songService.removeAllSongsFromPlaylist();
//        return "redirect:/home";
//    }

}
