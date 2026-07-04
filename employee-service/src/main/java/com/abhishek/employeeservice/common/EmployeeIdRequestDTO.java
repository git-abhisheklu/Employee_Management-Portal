package com.abhishek.employeeservice.common;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeIdRequestDTO {
    @NotNull
    private String id;
}