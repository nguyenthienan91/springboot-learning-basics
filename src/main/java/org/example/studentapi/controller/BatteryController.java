package org.example.studentapi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.example.studentapi.entity.Battery;
import org.example.studentapi.model.request.BatteryRequest;
import org.example.studentapi.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name="api")

public class BatteryController {
    @Autowired
    private BatteryService batteryService;

    @PostMapping("/api/battery")
    public ResponseEntity<Battery> createBattery(@Valid @RequestBody BatteryRequest batteryRequest) {
        Battery createdBattery = batteryService.createBattery(batteryRequest);
        return new ResponseEntity<>(createdBattery, HttpStatus.CREATED);
    }

    @GetMapping("/api/batteries")
    public ResponseEntity<List<Battery>> getAllBatteries() {
        List<Battery> batteries = batteryService.getAllBatteries();
        return ResponseEntity.ok(batteries);
    }

    @GetMapping("/battery/{id}")
    public ResponseEntity<Battery> getBatteryById(@PathVariable Long id) {
        Battery battery = batteryService.getBatteryById(id);
        return ResponseEntity.ok(battery);
    }

    @PutMapping("/battery/{id}")
    public ResponseEntity<Battery> updateBattery(@PathVariable Long id, @Valid @RequestBody BatteryRequest batteryRequest) {
        Battery updatedBattery = batteryService.updateBattery(id, batteryRequest);
        return ResponseEntity.ok(updatedBattery);
    }

    @DeleteMapping("/battery/{id}")
    public ResponseEntity<Void> deleteBattery(@PathVariable Long id) {
        batteryService.deleteBattery(id);
        return ResponseEntity.noContent().build();
    }
}
