package com.abhishek.employeeservice.Service;

import com.abhishek.employeeservice.Entity.Employee;
import com.abhishek.employeeservice.common.EmployeeRequestDTO;
import com.abhishek.employeeservice.common.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    public EmployeeResponseDTO getEmployeeById(Long id);

    public List<EmployeeResponseDTO> getAllEmployees();

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employee);

    public String updateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO);

    public String deleteEmployeeById(String id);
}