package org.example.studentapi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.example.studentapi.entity.Model;
import org.example.studentapi.model.request.ModelRequest;
import org.example.studentapi.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name="api")
@RequestMapping("/api/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @PostMapping
    public ResponseEntity<Model> createModel(@Valid @RequestBody ModelRequest modelRequest) {
        Model createdModel = modelService.createModel(modelRequest);
        return new ResponseEntity<>(createdModel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Model>> getAllModels(){
        List<Model> models = modelService.getAllModels();
        return ResponseEntity.ok(models);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Long id) {
        Model model = modelService.getModelById(id);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable Long id, @Valid @RequestBody ModelRequest modelRequest) {
        Model modelToUpdate = modelService.updateModel(id, modelRequest);
        return ResponseEntity.ok(modelToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }
}
