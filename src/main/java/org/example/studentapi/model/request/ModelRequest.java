package org.example.studentapi.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class ModelRequest {

    @NotBlank(message = "name cannot empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "description cannot empty")
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    @NotNull
    private Date release_At;
}
