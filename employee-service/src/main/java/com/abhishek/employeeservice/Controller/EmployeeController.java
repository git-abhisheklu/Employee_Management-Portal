package com.abhishek.employeeservice.Controller;

import com.abhishek.employeeservice.Service.EmployeeService;
import com.abhishek.employeeservice.common.EmployeeRequestDTO;
import com.abhishek.employeeservice.common.EmployeeResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee-service")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping(value = "/{empId}", produces = "application/json")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable String empId) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(Long.valueOf(empId)));
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees() {
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO requestDTO) {
        return ResponseEntity.ok().body(employeeService.createEmployee(requestDTO));
    }

    @PutMapping(value = "/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return employeeService.updateEmployee(id, employeeRequestDTO);
    }
}
