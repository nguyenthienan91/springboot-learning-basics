package org.example.studentapi.service;

import org.example.studentapi.entity.Bike;
import org.example.studentapi.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BikeService {
    @Autowired
    BikeRepository bikeRepository;

    @Autowired
    AuthenticationService authenticationService;

    public Bike createBike(Bike bike){
        bike.setAccount(authenticationService.getCurrentAccount());
        return bikeRepository.save(bike);
    }
}
