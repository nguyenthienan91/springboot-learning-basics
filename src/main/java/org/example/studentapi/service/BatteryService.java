package org.example.studentapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.studentapi.entity.Battery;
import org.example.studentapi.entity.Model;
import org.example.studentapi.exception.exceptions.ResourceNotFoundException;
import org.example.studentapi.model.request.BatteryRequest;
import org.example.studentapi.repository.BatteryRepository;
import org.example.studentapi.repository.ModelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatteryService {

    @Autowired
    private final BatteryRepository batteryRepository;
    @Autowired
    private final ModelRepository modelRepository;
    @Autowired
    private final ModelMapper modelMapper;

    @Transactional
    public Battery createBattery(BatteryRequest batteryRequest) {
        Model model = modelRepository.findById(batteryRequest.getModel_id()).orElseThrow(() -> new ResourceNotFoundException("Model not found"));

        Battery newBattery = modelMapper.map(batteryRequest, Battery.class);
        newBattery.setId(0);

        newBattery.setModel(model);
        newBattery.setDeleted(false);

        return batteryRepository.save(newBattery);

    }

    @Transactional
    public List<Battery> getAllBatteries() {
        return batteryRepository.findAll();
    }

    @Transactional
    public Battery getBatteryById(Long id) {
        return batteryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Battery not found with id: " + id));
    }

    @Transactional
    public Battery updateBattery(Long id, BatteryRequest batteryRequest) {
        // 1. Find the existing battery
        Battery existingBattery = getBatteryById(id);

        // 2. Find the (potentially new) parent Model
        Model model = modelRepository.findById(batteryRequest.getModel_id())
                .orElseThrow(() -> new ResourceNotFoundException("Model not found with id: " + batteryRequest.getModel_id()));

        // 3. Use ModelMapper to update simple fields on the existing entity.
        //    This avoids overwriting the ID and other managed state.
        modelMapper.map(batteryRequest, existingBattery);

        // 4. Manually update the relationship
        existingBattery.setModel(model);

        // 5. Save and return the updated battery
        return batteryRepository.save(existingBattery);
    }

    @Transactional
    public void deleteBattery(Long id) {
        Battery batteryToDelete = getBatteryById(id);
        batteryToDelete.setDeleted(true);
        batteryRepository.save(batteryToDelete);
    }

}
