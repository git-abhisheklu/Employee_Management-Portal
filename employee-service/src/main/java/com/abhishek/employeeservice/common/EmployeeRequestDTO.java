package com.abhishek.employeeservice.common;

import com.abhishek.employeeservice.common.validators.CreateEmployeeValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmployeeRequestDTO {
    private Long id;
    @NotBlank
    @Size(max = 20, message = " Name can't exceed 20 characters.")
    private String name;
    @NotBlank(message = "Email can't be empty.")
    @Email(message = "Please enter valid email.")
    private String email;
    @NotBlank(message = "Mobile number can't be empty.")
    @Size(max = 10)
    private String mobileNumber;
    @NotBlank(message = "Address can't be empty.")
    private String address;
    @NotBlank(message = "Date of birth can't be empty.")
    private String dateOfBirth;
    @NotBlank(message = "Salary can't be empty.")
    private String salary;
    @NotBlank(groups = CreateEmployeeValidationGroup.class, message = "Onboarding date can't be empty.")
    private String onboardingDate;
    @NotBlank(message = "Department can't be empty.")
    private String department;
}