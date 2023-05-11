package com.example.carcatalog.web;

import com.example.carcatalog.model.dto.CarSearchDTO;
import com.example.carcatalog.model.dto.CreateCarDTO;
import com.example.carcatalog.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarSearchDTO>> getAllCars() {

        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping
    public ResponseEntity<CreateCarDTO> createCar(@RequestBody CreateCarDTO newCar,
                                                  UriComponentsBuilder uriComponentsBuilder) {

        long newCarId = carService.createCar(newCar);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/cars/{id}").build(newCarId))
                .build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarSearchDTO> deleteCar(@PathVariable Long id) {//TODO: choose DTO

        carService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
