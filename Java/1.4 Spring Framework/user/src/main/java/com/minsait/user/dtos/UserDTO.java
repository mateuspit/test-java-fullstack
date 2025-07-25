package com.minsait.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "Name is mandatory.")
    @Size(min = 2, message = "Name too short.")
    private String name;
    
    @NotBlank(message = "Email is mandatory.")
    @Email(message = "Email not valid.")
    private String email;
}
