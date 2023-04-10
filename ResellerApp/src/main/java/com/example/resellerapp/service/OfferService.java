package com.example.resellerapp.service;


import com.example.resellerapp.model.dto.CreateOfferDTO;
import com.example.resellerapp.model.dto.OfferViewDTO;
import com.example.resellerapp.model.entity.Condition;
import com.example.resellerapp.model.entity.Offer;
import com.example.resellerapp.model.entity.User;
import com.example.resellerapp.repository.ConditionRepo;
import com.example.resellerapp.repository.OfferRepo;
import com.example.resellerapp.repository.UserRepo;
import com.example.resellerapp.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepo offerRepo;

    private final UserRepo userRepo;

    private final ConditionRepo conditionRepo;

    private final LoggedUser loggedUser;


    public OfferService(OfferRepo offerRepo, UserRepo userRepo, ConditionRepo conditionRepo, LoggedUser loggedUser) {
        this.offerRepo = offerRepo;
        this.userRepo = userRepo;
        this.conditionRepo = conditionRepo;
        this.loggedUser = loggedUser;
    }

    public boolean create(CreateOfferDTO createOfferDTO) {

        Condition byName = this.conditionRepo.findByName(createOfferDTO.getConditionName()).orElseThrow();

        Offer offer = Offer.builder()
                .condition(byName)
                .description(createOfferDTO.getDescription())
                .price(createOfferDTO.getPrice())
                .user(getLoggedUser(loggedUser))
                .build();

        this.offerRepo.save(offer);

        return true;
    }

    private OfferViewDTO viewOfferDTO(Offer offer) {

        return OfferViewDTO.builder()
                .id(offer.getId())
                .description(offer.getDescription())
                .user(offer.getUser())
                .price(offer.getPrice())
                .condition(offer.getCondition())
                .build();
}

    public Set<OfferViewDTO> getOwnOffers() {

        return getLoggedUser(loggedUser)
                .getOffers()
                .stream()
                .map(this::viewOfferDTO)
                .collect(Collectors.toSet());
    }

    public Set<OfferViewDTO> getOtherOffers() {

        Set<Offer> forSale = this.offerRepo.findAllByUserNot(getLoggedUser(loggedUser));

        return forSale
                .stream()
                .map(this::viewOfferDTO)
                .collect(Collectors.toSet());
    }

    public void removeOffer(Long id) {

        Offer byId = this.offerRepo.findById(id).orElseThrow();

        this.offerRepo.delete(byId);
    }

    public Set<OfferViewDTO> boughtOffers() {

        return getLoggedUser(loggedUser)
                .getBoughtOffers()
                .stream()
                .map(this::viewOfferDTO)
                .collect(Collectors.toSet());
    }

    public void buyNow(Long offerId) {

        Offer byId = this.offerRepo.findById(offerId).orElseThrow();

        User buyer = getLoggedUser(loggedUser);
        buyer.getBoughtOffers().add(byId);
        buyer.setBoughtOffers(buyer.getBoughtOffers());

        byId.setUser(null);

        this.userRepo.save(buyer);
        this.offerRepo.save(byId);

    }


    public User getLoggedUser(LoggedUser loggedUser) {
        return this.userRepo.findById(loggedUser.getId()).orElseThrow();
    }


}


