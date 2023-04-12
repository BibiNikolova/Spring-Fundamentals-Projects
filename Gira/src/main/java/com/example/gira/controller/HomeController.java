package com.example.gira.controller;

import com.example.gira.model.dto.TaskViewDTO;
import com.example.gira.service.AuthService;
import com.example.gira.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {


    private final AuthService authService;
    private final TaskService taskService;

    @Autowired
    public HomeController(AuthService authService, TaskService taskService) {
        this.authService = authService;
        this.taskService = taskService;
    }

    @ModelAttribute("taskViewDTO")
    public TaskViewDTO initMyTaskVisualForm() {
        return new TaskViewDTO();
    }

    @GetMapping("/home")
    public String loggedInHome(Model model) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        model.addAttribute("allTasks", this.taskService.getTasks());

        return "home";
    }

    @GetMapping("/")
    public String loggedOutIndex() {

        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/tasks/progress/{taskId}")
    public String changeProgress(@PathVariable Long taskId) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.taskService.changeProgress(taskId);
        return "redirect:/home";
    }
}
