package com.example.coffeeshopapp.controller;


import com.example.coffeeshopapp.model.dto.CreateOrderDTO;
import com.example.coffeeshopapp.service.AuthService;
import com.example.coffeeshopapp.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private final AuthService authService;

    private final OrderService orderService;

    public OrderController(AuthService authService, OrderService orderService) {
        this.authService = authService;
        this.orderService = orderService;
    }


    @ModelAttribute("createOrderDTO")
    public CreateOrderDTO initCreateOrderDTO(){
        return new CreateOrderDTO();
    }

    @GetMapping("/orders/add")
    public String orders() {

        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "order-add";
    }

    @PostMapping("/orders/add")
    public String songs(@Valid CreateOrderDTO createOrderDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if(!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if(bindingResult.hasErrors() || !this.orderService.create(createOrderDTO)){
            redirectAttributes.addFlashAttribute("createOrderDTO", createOrderDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createOrderDTO", bindingResult);

            return "redirect:/orders/add";
        }
        return "redirect:/home";
    }
}
