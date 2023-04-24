package com.example.shoppinglistapp.controller;

import com.example.shoppinglistapp.model.dto.CreateProductDTO;
import com.example.shoppinglistapp.service.AuthService;
import com.example.shoppinglistapp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final AuthService authService;

    private  final ProductService productService;

    public ProductController(AuthService authService, ProductService productService) {
        this.authService = authService;
        this.productService = productService;
    }

    @ModelAttribute("createProductDTO")
    public CreateProductDTO initCreateProductDTO(){
        return new CreateProductDTO();
    }

    @GetMapping("/product/add")
    public String products() {

        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "product-add";
    }

    @PostMapping("/product/add")
    public String songs(@Valid CreateProductDTO createProductDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if(bindingResult.hasErrors() || !this.productService.create(createProductDTO)){
            redirectAttributes.addFlashAttribute("createProductDTO", createProductDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createProductDTO", bindingResult);

            return "redirect:/product/add";
        }
        return "redirect:/home";
    }
}

