package org.example.studentapi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.studentapi.entity.Bike;
import org.example.studentapi.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "api")
@RequestMapping("/api/bike")
public class BikeController {
    @Autowired
    BikeService bikeService;

    @PostMapping
    public ResponseEntity createBike(@RequestBody Bike bike) {
        Bike newBike = bikeService.createBike(bike);
        return ResponseEntity.ok(newBike);
    }
}
