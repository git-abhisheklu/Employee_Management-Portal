package com.abhishek.employeeservice.Controller;

import com.abhishek.employeeservice.Service.EmployeeService;
import com.abhishek.employeeservice.common.EmployeeRequestDTO;
import com.abhishek.employeeservice.common.EmployeeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee-service")
@AllArgsConstructor
@Tag(name = "Employee", description = "API for managing Employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping(value = "/{empId}", produces = "application/json")
    @Operation(summary = "Get Employee by employee id.")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable String empId) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(Long.valueOf(empId)));
    }

    @GetMapping(value = "/", produces = "application/json")
    @Operation(summary = "Get Employees")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees() {
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    @PostMapping(value = "/", consumes = "application/json")
    @Operation(summary = "Save Employee")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO requestDTO) {
        return ResponseEntity.ok().body(employeeService.createEmployee(requestDTO));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update Employee")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return ResponseEntity.ok().body(employeeService.updateEmployee(id, employeeRequestDTO));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete Employee by employee id")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable String id){
        return ResponseEntity.ok().body(employeeService.deleteEmployeeById(id));
    }
}
