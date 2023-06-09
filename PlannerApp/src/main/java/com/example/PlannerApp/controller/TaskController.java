package com.example.PlannerApp.controller;


import com.example.PlannerApp.model.dto.CreateTaskDTO;
import com.example.PlannerApp.service.AuthService;
import com.example.PlannerApp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskController {

    private final AuthService authService;

    private final TaskService taskService;

    public TaskController(AuthService authService, TaskService taskService) {
        this.authService = authService;
        this.taskService = taskService;
    }

    @ModelAttribute("createTaskDTO")
    public CreateTaskDTO initCreateTaskDTO() {
        return new CreateTaskDTO();
    }

    @GetMapping("/tasks/add")
    public String tasks() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "task-add";
    }
    @PostMapping("/tasks/add")
    public String tasks(@Valid CreateTaskDTO createTaskDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.taskService.create(createTaskDTO)) {
            redirectAttributes.addFlashAttribute("createTaskDTO", createTaskDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.createTaskDTO)", bindingResult);

            return "redirect:/tasks/add";
        }
        return "redirect:/home";
    }



}
