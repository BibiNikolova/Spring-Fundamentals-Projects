package com.example.gira.service;


import com.example.gira.model.dto.CreateTaskDTO;
import com.example.gira.model.dto.TaskViewDTO;
import com.example.gira.model.entity.Classification;
import com.example.gira.model.entity.Task;
import com.example.gira.model.entity.User;
import com.example.gira.model.enums.Progress;
import com.example.gira.repository.ClassificationRepo;
import com.example.gira.repository.TaskRepo;
import com.example.gira.repository.UserRepo;
import com.example.gira.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepo taskRepo;

    private final UserRepo userRepo;

    private final ClassificationRepo classRepo;

    private final LoggedUser loggedUser;

    @Autowired
    public TaskService(TaskRepo taskRepo, UserRepo userRepo, ClassificationRepo classRepo, LoggedUser loggedUser) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
        this.classRepo = classRepo;
        this.loggedUser = loggedUser;
    }

    public boolean create(CreateTaskDTO createTaskDTO) {

        Classification byName = this.classRepo.findByClassificationName(createTaskDTO.getClassificationName()).orElseThrow();
        Task task = Task.builder()
                .name(createTaskDTO.getName())
                .description(createTaskDTO.getDescription())
                .progress(Progress.OPEN)
                .dueDate(createTaskDTO.getDueDate())
                .classification(byName)
                .user(getLoggedUser(loggedUser))
                .build();

        this.taskRepo.save(task);

        return true;
    }

    private TaskViewDTO viewTaskDTO(Task task) {

        return TaskViewDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .user(task.getUser())
                .classificationName(task.getClassification().getClassificationName())
                .dueDate(task.getDueDate())
                .progress(task.getProgress())
                .build();
    }

    public Set<TaskViewDTO> getTasks() {

        return this.taskRepo.findAll()
                .stream()
                .map(this::viewTaskDTO)
                .collect(Collectors.toSet());
    }

    public void changeProgress(Long id) {
        Task task = this.taskRepo.findById(id).orElseThrow();

        switch (task.getProgress()) {
            case OPEN -> {
                task.setProgress(Progress.IN_PROGRESS);
                this.taskRepo.save(task);
            }
            case IN_PROGRESS -> {
                task.setProgress(Progress.COMPLETED);
                this.taskRepo.save(task);
            }
            case COMPLETED -> this.taskRepo.delete(task);
        }
    }

    public User getLoggedUser(LoggedUser loggedUser) {
        return this.userRepo.findById(loggedUser.getId()).orElseThrow();
    }

}


