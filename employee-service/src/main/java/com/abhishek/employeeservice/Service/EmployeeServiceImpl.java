package com.abhishek.employeeservice.Service;

import com.abhishek.employeeservice.Entity.Employee;
import com.abhishek.employeeservice.Repository.EmployeeRepository;
import com.abhishek.employeeservice.common.EmployeeRequestDTO;
import com.abhishek.employeeservice.common.EmployeeResponseDTO;
import com.abhishek.employeeservice.exception.EmailAlreadyExistsException;
import com.abhishek.employeeservice.exception.EmployeeNotFoundException;
import com.abhishek.employeeservice.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with the id " + id + " is not present. ");
        }
        return employee.map(EmployeeMapper::toDTO).orElseGet(EmployeeResponseDTO::new);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(EmployeeMapper::toDTO).toList();
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Boolean existsByEmail = employeeRepository.existsByEmail(employeeRequestDTO.getEmail());
        if (existsByEmail) {
            throw new EmailAlreadyExistsException("An employee with the email id: " + employeeRequestDTO.getEmail() + " is already present.");
        }
        Employee saved = employeeRepository.save(EmployeeMapper.toEntity(employeeRequestDTO));
        return EmployeeMapper.toDTO(saved);
    }

    @Override
    public String updateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO) {
        Optional<Employee> byId = employeeRepository.findById(id);
        if (byId.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with the given id " + id + " is not present.");
        }
        if (employeeRepository.existsByEmailAndIdNot(employeeRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("An employee with the email id: " + employeeRequestDTO.getEmail() + " is already present.");
        }
        Employee employee = byId.get();
        employee.setId(employeeRequestDTO.getId());
        employee.setName(employeeRequestDTO.getName());
        employee.setAddress(employeeRequestDTO.getAddress());
        employee.setDepartment(employeeRequestDTO.getDepartment());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setSalary(Double.valueOf(employeeRequestDTO.getSalary()));
        employee.setDateOfBirth(LocalDate.parse(employeeRequestDTO.getDateOfBirth()));
        employee.setMobileNumber(Long.valueOf(employeeRequestDTO.getMobileNumber()));
        Employee saved = employeeRepository.save(employee);
        return "Employee has been updated.";
    }

    @Override
    public String deleteEmployeeById(String id) {
        Optional<Employee> byId = employeeRepository.findById(Long.valueOf(id));
        if (byId.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with the id " + id + " is not present. ");
        }
        employeeRepository.deleteById(Long.valueOf(id));
        return "Employee with the id " + id + " has been deleted.";
    }

}