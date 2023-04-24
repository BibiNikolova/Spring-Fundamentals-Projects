package com.example.shoppinglistapp.controller;

import com.example.shoppinglistapp.model.dto.ProductByCategoryDTO;
import com.example.shoppinglistapp.model.dto.ProductViewDTO;
import com.example.shoppinglistapp.model.enums.CategoryName;
import com.example.shoppinglistapp.service.AuthService;
import com.example.shoppinglistapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ProductService productService;
    private final AuthService authService;

    @ModelAttribute("productViewDTO")
    public ProductViewDTO initPlaylistVisualForm() {
        return new ProductViewDTO();
    }

    @ModelAttribute("productByCategoryDTO")
    public ProductByCategoryDTO initSet() {
        return new ProductByCategoryDTO();
    }

    @Autowired
    public HomeController(ProductService productService, AuthService authService) {
        this.productService = productService;
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

        model.addAttribute("product", this.productService.getProducts());
        model.addAttribute("totalPrice", this.productService.getTotalPrice());

        return "home";
    }

    @GetMapping("/home/buy/{id}")
    public String buyProduct(@PathVariable Long id) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.productService.buyProduct(id);
        return "redirect:/home";
    }

    @GetMapping("/home/buyAll")
    public String buyAllProducts() {


        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.productService.buyAllProducts();

        return "redirect:/home";
    }
}

