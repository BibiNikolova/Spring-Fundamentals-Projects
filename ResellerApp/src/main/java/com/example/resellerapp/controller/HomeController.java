package com.example.resellerapp.controller;

import com.example.resellerapp.model.dto.OfferViewDTO;
import com.example.resellerapp.service.AuthService;
import com.example.resellerapp.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final OfferService offerService;
    private final AuthService authService;


    @ModelAttribute("offerViewDTO")
    public OfferViewDTO initMyOfferVisualForm() {
        return new OfferViewDTO();
    }

    @Autowired
    public HomeController(OfferService offerService, AuthService authService) {
        this.offerService = offerService;
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
    public String loggedInHome(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        model.addAttribute("ownOffers", this.offerService.getOwnOffers());
        model.addAttribute("otherOffers", this.offerService.getOtherOffers());
        model.addAttribute("bought", this.offerService.boughtOffers());

        return "home";
    }

    @GetMapping("/home/buy/{offerId}")
    public String buyOffer(@PathVariable Long offerId) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.offerService.buyNow(offerId);
        return "redirect:/home";
    }

    @GetMapping("/home/remove/{offerId}")
    public String removeOffer(@PathVariable Long offerId) {


        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.offerService.removeOffer(offerId);

        return "redirect:/home";
    }
}

