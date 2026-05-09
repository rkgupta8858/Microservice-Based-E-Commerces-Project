package com.ecom.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
    private String mobile;

    @NotBlank(message = "Password is required")
    private String password;
}
