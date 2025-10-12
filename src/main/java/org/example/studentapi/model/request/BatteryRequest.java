package org.example.studentapi.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatteryRequest {
    @NotBlank(message = "name is required")
    @Size(min = 2, max = 50, message = "name must be between 2 and 50 characters")
    private String Name;

    @NotBlank(message = "description is required")
    @Size(max = 255, message = "description cannot exceed 255 characters")
    private String description;

    @NotNull(message = "model id is required")
    private Long model_id;

}
