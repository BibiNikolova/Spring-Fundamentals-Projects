package com.example.PlannerApp.service;


import com.example.PlannerApp.model.dto.CreateTaskDTO;
import com.example.PlannerApp.model.dto.TaskViewDTO;
import com.example.PlannerApp.model.entity.Priority;
import com.example.PlannerApp.model.entity.Task;
import com.example.PlannerApp.model.entity.User;
import com.example.PlannerApp.repository.PriorityRepo;
import com.example.PlannerApp.repository.TaskRepo;
import com.example.PlannerApp.repository.UserRepo;
import com.example.PlannerApp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    private final UserRepo userRepo;

    private final PriorityRepo priorityRepo;

    private final LoggedUser loggedUser;

    @Autowired
    public TaskService(TaskRepo taskRepo, UserRepo userRepo, PriorityRepo priorityRepo, LoggedUser loggedUser) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
        this.priorityRepo = priorityRepo;
        this.loggedUser = loggedUser;
    }

    public boolean create(CreateTaskDTO createTaskDTO) {

        Priority byName = this.priorityRepo.findByPriorityName(createTaskDTO.getPriorityName()).orElseThrow();
        Task task = Task.builder()
                .description(createTaskDTO.getDescription())
                .dueDate(createTaskDTO.getDueDate())
                .priority(byName)
                .build();

        this.taskRepo.save(task);

        return true;
    }

    private TaskViewDTO viewTaskDTO(Task task) {

        return TaskViewDTO.builder()
                .id(task.getId())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .priorityName(task.getPriority().getPriorityName())
                .build();
    }

    public Set<TaskViewDTO> getAssignedTasks() {

        Set<Task> assignedByUser = this.taskRepo.findAllByUser(getLoggedUser(loggedUser));

        return assignedByUser
                .stream()
                .map(this::viewTaskDTO)
                .collect(Collectors.toSet());
    }

    public Set<TaskViewDTO> getTasks() {

        Set<Task> nullable = this.taskRepo.findAllByUser(null);

        return nullable
                .stream()
                .map(this::viewTaskDTO)
                .collect(Collectors.toSet());
    }

    public void removeTask(Long id) {

        Task byId = this.taskRepo.findById(id).orElseThrow();

        User assignor = getLoggedUser(loggedUser);
        assignor.removeTask(id);

        byId.setUser(null);

        this.userRepo.save(assignor);
        this.taskRepo.delete(byId);

    }

    public void assign(Long taskId) {

        Task byId = this.taskRepo.findById(taskId).orElseThrow();

        User assignor = getLoggedUser(loggedUser);
        assignor.addTask(byId);

        byId.setUser(assignor);

        this.userRepo.save(assignor);
        this.taskRepo.save(byId);

    }

    public void returnTask(Long taskId) {

        Task byId = this.taskRepo.findById(taskId).orElseThrow();

        User assignor = getLoggedUser(loggedUser);
        assignor.removeTask(taskId);

        byId.setUser(null);

        this.userRepo.save(assignor);
        this.taskRepo.save(byId);

    }

    public User getLoggedUser(LoggedUser loggedUser) {
        return this.userRepo.findById(loggedUser.getId()).orElseThrow();
    }

}


