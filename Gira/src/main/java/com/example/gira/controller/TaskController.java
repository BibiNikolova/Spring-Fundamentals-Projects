package com.example.gira.controller;

import com.example.gira.model.dto.CreateTaskDTO;
import com.example.gira.model.dto.TaskViewDTO;
import com.example.gira.service.AuthService;
import com.example.gira.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskController {

    private final AuthService authService;

    private final TaskService taskService;

    @Autowired
    public TaskController(AuthService authService, TaskService taskService) {
        this.authService = authService;
        this.taskService = taskService;
    }

    @ModelAttribute("createTaskDTO")
    public CreateTaskDTO initCreateTaskDTO() {
        return new CreateTaskDTO();
    }

    @ModelAttribute("taskViewDTO")
    public TaskViewDTO initMyTaskVisualForm() {
        return new TaskViewDTO();
    }


    @GetMapping("/tasks/add")
    public String tasks() {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "add-task";
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


    @GetMapping("/tasks/progress/{taskId}")
    public String changeProgress(@PathVariable Long taskId) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        this.taskService.changeProgress(taskId);
        return "redirect:/home";
    }

}
