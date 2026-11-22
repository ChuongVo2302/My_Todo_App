package com.example.My_Todo_App.dto;

import jakarta.validation.constraints.NotBlank;

public record TodoRequestDTO(@NotBlank String title, @NotBlank String description, boolean status) {
    
}
