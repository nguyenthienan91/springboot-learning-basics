package org.example.studentapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.studentapi.entity.Model;
import org.example.studentapi.exception.exceptions.ResourceNotFoundException;
import org.example.studentapi.model.request.ModelRequest;
import org.example.studentapi.repository.ModelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new Model using ModelMapper to map the DTO.
     */
    @Transactional
    public Model createModel(ModelRequest modelRequest) {
        Model newModel = modelMapper.map(modelRequest, Model.class);
        return modelRepository.save(newModel);
    }

    //Trả về danh sách các model
    @Transactional
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Transactional
    public Model getModelById(Long id){
        return modelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Model not found"));
    }

    @Transactional
    public Model updateModel(Long id, ModelRequest modelRequest) {
        Model existingModel = getModelById(id);
        modelMapper.map(modelRequest, existingModel);
        return modelRepository.save(existingModel);
    }

    @Transactional
    public void deleteModel(Long id){
        Model modelToDelete = getModelById(id);
        modelToDelete.set_deleted(true);
        modelRepository.save(modelToDelete);
    }
}
