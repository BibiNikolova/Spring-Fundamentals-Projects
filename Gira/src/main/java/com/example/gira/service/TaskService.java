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
                .build();

        this.taskRepo.save(task);

        return true;
    }
//
//    private TaskViewDTO viewTaskDTO(Task task) {
//
//        return TaskViewDTO.builder()
//                .id(task.getId())
//                .description(task.getDescription())
//                .user(task.getUser())
//                .price(task.getPrice())
//                .condition(task.getCondition())
//                .build();
//    }
//
//    public Set<OfferViewDTO> getOwnOffers() {
//
//        return getLoggedUser(loggedUser)
//                .getOffers()
//                .stream()
//                .map(this::viewOfferDTO)
//                .collect(Collectors.toSet());
//    }
//
//    public Set<OfferViewDTO> getOtherOffers() {
//
//        Set<Offer> forSale = this.offerRepo.findAllByUserNot(getLoggedUser(loggedUser));
//
//        return forSale
//                .stream()
//                .map(this::viewOfferDTO)
//                .collect(Collectors.toSet());
//    }
//
//    public void removeOffer(Long id) {
//
//        Offer byId = this.offerRepo.findById(id).orElseThrow();
//
//        this.offerRepo.delete(byId);
//    }
//
//    public Set<OfferViewDTO> boughtOffers() {
//
//        return getLoggedUser(loggedUser)
//                .getBoughtOffers()
//                .stream()
//                .map(this::viewOfferDTO)
//                .collect(Collectors.toSet());
//    }
//
//    public void buyNow(Long offerId) {
//
//        Offer byId = this.offerRepo.findById(offerId).orElseThrow();
//
//        User buyer = getLoggedUser(loggedUser);
//        buyer.getBoughtOffers().add(byId);
//        buyer.setBoughtOffers(buyer.getBoughtOffers());
//
//        byId.setUser(null);
//
//        this.userRepo.save(buyer);
//        this.offerRepo.save(byId);

//    }


    public User getLoggedUser(LoggedUser loggedUser) {
        return this.userRepo.findById(loggedUser.getId()).orElseThrow();
    }


}


