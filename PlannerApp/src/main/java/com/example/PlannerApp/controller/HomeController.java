package com.example.PlannerApp.controller;

import com.example.PlannerApp.model.dto.TaskViewDTO;
import com.example.PlannerApp.service.AuthService;
import com.example.PlannerApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final TaskService taskService;
    private final AuthService authService;

    @Autowired
    public HomeController(TaskService taskService, AuthService authService) {
        this.taskService = taskService;
        this.authService = authService;

    }

    @ModelAttribute("taskViewDTO")
    public TaskViewDTO initMyTaskVisualForm() {
        return new TaskViewDTO();
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

        model.addAttribute("ownTasks", this.taskService.getOwnTasks());
        model.addAttribute("others", this.taskService.getOtherTasks());

        return "home";
    }

    @GetMapping("/home/assign/{taskId}")
    public String assignTask(@PathVariable Long taskId) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.taskService.assign(taskId);

        return "redirect:/home";
    }

    @GetMapping("/home/return/{taskId}")
    public String returnTask(@PathVariable Long taskId) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.taskService.returnTask(taskId);

        return "redirect:/home";
    }

    @GetMapping("/home/remove/{taskId}")
    public String removeTask(@PathVariable Long taskId) {


        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.taskService.removeTask(taskId);

        return "redirect:/home";
    }
}

