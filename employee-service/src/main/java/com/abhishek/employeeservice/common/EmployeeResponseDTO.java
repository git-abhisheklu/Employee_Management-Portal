package com.abhishek.employeeservice.common;

import com.abhishek.employeeservice.Entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Long mobileNumber;
    private String department;
}
