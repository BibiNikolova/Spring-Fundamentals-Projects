package com.example.carcatalog.service;

import com.example.carcatalog.model.dto.CarSearchDTO;
import com.example.carcatalog.model.dto.CreateCarDTO;
import com.example.carcatalog.model.entity.Car;
import com.example.carcatalog.model.entity.FuelType;
import com.example.carcatalog.model.entity.Model;
import com.example.carcatalog.model.entity.Transmission;
import com.example.carcatalog.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final BrandRepo brandRepo;
    private final FuelTypeRepo fuelTypeRepo;
    private final ModelRepo modelRepo;
    private final TransmissionRepo transmissionRepo;
    private final CarRepo carRepo;

    public CarService(BrandRepo brandRepo, FuelTypeRepo fuelTypeRepo, ModelRepo modelRepo, TransmissionRepo transmissionRepo, CarRepo carRepo) {
        this.brandRepo = brandRepo;
        this.fuelTypeRepo = fuelTypeRepo;
        this.modelRepo = modelRepo;
        this.transmissionRepo = transmissionRepo;
        this.carRepo = carRepo;
    }

    public List<CarSearchDTO> getAllCars() {
        return carRepo.findAll()//TODO: how to insert from db by price desc
                .stream()
                .map(this::map)
                .toList();
    }

    private CarSearchDTO map(Car car) {

        return CarSearchDTO.builder()
                .id(car.getId())
                .model(car.getModel())
                .brand(car.getModel().getBrand())
                .price(car.getPrice())
                .fuelType(car.getFuelType())
                .registrationDate(car.getRegistrationDate())
                .transmission(car.getTransmission())
                .build();
    }

    public long createCar(CreateCarDTO newCar) {
        Model model = this.modelRepo.findByModelName(newCar.getModelName()).orElseThrow();
        Transmission transmission = this.transmissionRepo.findByTransmissionName(newCar.getTransmissionName()).orElseThrow();
        FuelType fuel = this.fuelTypeRepo.findByFuelTypeName(newCar.getFuelTypeName()).orElseThrow();

        Car car = Car.builder()
                .vinNumber(newCar.getVinNumber())
                .model(model)
                .price(newCar.getPrice())
                .registrationDate(newCar.getRegistrationDate())
                .transmission(transmission)
                .fuelType(fuel)
                .remarks(newCar.getRemarks())
                .build();

        return this.carRepo.save(car).getId();
    }

    public void deleteById(Long id) {
        carRepo.deleteById(id);
    }

}
