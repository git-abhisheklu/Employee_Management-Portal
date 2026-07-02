package com.abhishek.employeeservice.mapper;

import com.abhishek.employeeservice.Entity.Employee;
import com.abhishek.employeeservice.common.EmployeeRequestDTO;
import com.abhishek.employeeservice.common.EmployeeResponseDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {
    public static EmployeeResponseDTO toDTO(Employee emp) {
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        responseDTO.setId(emp.getId());
        responseDTO.setName(emp.getName());
        responseDTO.setDepartment(emp.getDepartment());
        responseDTO.setEmail(emp.getEmail());
        responseDTO.setMobileNumber(emp.getMobileNumber());
        return responseDTO;
    }

    public static Employee toEntity(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        employee.setId(employeeRequestDTO.getId());
        employee.setName(employeeRequestDTO.getName());
        employee.setDepartment(employeeRequestDTO.getDepartment());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setMobileNumber(employeeRequestDTO.getMobileNumber());
        employee.setSalary(employeeRequestDTO.getSalary());
        employee.setAddress(employeeRequestDTO.getAddress());
        employee.setDateOfBirth(employeeRequestDTO.getDateOfBirth());
        return employee;
    }
}
