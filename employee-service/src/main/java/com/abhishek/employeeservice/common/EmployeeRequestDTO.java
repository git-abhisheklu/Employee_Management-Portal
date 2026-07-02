package com.abhishek.employeeservice.common;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequestDTO {
    @NotBlank
    private Long id;
    @NotBlank
    @Size(max = 20, message = " Name can't exceed 20 characters.")
    private String name;
    @NotBlank(message = "Email can't be null." )
    @Email(message = "Please enter valid email.")
    private String email;
    @NotBlank
    @Size(max = 10)
    private Long mobileNumber;
    private String address;
    private LocalDate dateOfBirth;
    private Double salary;
    private String department;
}
