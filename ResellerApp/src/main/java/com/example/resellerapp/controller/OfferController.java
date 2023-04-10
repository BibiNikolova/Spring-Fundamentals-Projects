package com.example.resellerapp.controller;

import com.example.resellerapp.model.dto.CreateOfferDTO;
import com.example.resellerapp.service.AuthService;
import com.example.resellerapp.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OfferController {

    private final AuthService authService;

    private  final OfferService offerService;

    public OfferController(AuthService authService, OfferService offerService) {
        this.authService = authService;
        this.offerService = offerService;
    }

    @ModelAttribute("createOfferDTO")
    public CreateOfferDTO initCreatePostDTO(){
        return new CreateOfferDTO();
    }

    @GetMapping("/offer/add")
    public String offers() {

        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "offer-add";
    }

    @PostMapping("/offer/add")
    public String offers(@Valid CreateOfferDTO createOfferDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if(bindingResult.hasErrors() || !this.offerService.create(createOfferDTO)){
            redirectAttributes.addFlashAttribute("createOfferDTO", createOfferDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createOfferDTO)", bindingResult);

            return "redirect:/offer/add";
        }
        return "redirect:/home";
    }
}
