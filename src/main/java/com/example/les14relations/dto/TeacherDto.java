package com.example.les14relations.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TeacherDto {
    public Long id;
    public String firstName;
    @NotBlank
    @Size(min=2, max=64)
    public String lastName;
    public LocalDate dob;
    @Email(message = "invalide emailadres")
    public String email;
}
