package com.example.coffeeshopapp.controller;

import com.example.coffeeshopapp.model.dto.EmployeesOrdersDTO;
import com.example.coffeeshopapp.model.dto.OrderViewDTO;
import com.example.coffeeshopapp.service.AuthService;
import com.example.coffeeshopapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    private final OrderService orderService;
    private final AuthService authService;

    @ModelAttribute("OrderViewDTO")
    public OrderViewDTO initOrders() {
        return new OrderViewDTO();
    }
    @ModelAttribute("EmployeesOrdersDTO")
    public EmployeesOrdersDTO initByEmployee() {
        return  new EmployeesOrdersDTO();
    }

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

        model.addAttribute("allOrders", this.orderService.getAllOrders());
        model.addAttribute("timeToPrepare", this.orderService.getTotalTimeToPrepare());
        model.addAttribute("byEmployee", this.orderService.getOrdersByEmployee()
                .stream().sorted((f, s) -> Integer.compare(s.getNumOfOrders(), f.getNumOfOrders())));

        return "home";

    }


}
