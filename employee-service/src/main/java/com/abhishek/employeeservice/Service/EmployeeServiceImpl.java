package com.abhishek.employeeservice.Service;

import com.abhishek.employeeservice.Entity.Employee;
import com.abhishek.employeeservice.Repository.EmployeeRepository;
import com.abhishek.employeeservice.common.EmployeeRequestDTO;
import com.abhishek.employeeservice.common.EmployeeResponseDTO;
import com.abhishek.employeeservice.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(EmployeeMapper::toDTO).orElseGet(EmployeeResponseDTO::new);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream().map(EmployeeMapper::toDTO).toList();
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employee) {
        Employee saved = employeeRepository.save( EmployeeMapper.toEmployee(employee));
        return EmployeeMapper.toDTO(saved);
    }

    @Override
    public String updateEmployee(Long id, EmployeeRequestDTO employeeRequestDTO) {
        Optional<Employee> byId = employeeRepository.findById(id);
        if (byId.isPresent()) {
            Employee employee = byId.get();
            employee.setId(employeeRequestDTO.getId());
            employee.setName(employeeRequestDTO.getName());
            employee.setAddress(employeeRequestDTO.getAddress());
            employee.setDepartment(employeeRequestDTO.getDepartment());
            employee.setEmail(employeeRequestDTO.getEmail());
            employee.setSalary(employeeRequestDTO.getSalary());
            employee.setDateOfBirth(employeeRequestDTO.getDateOfBirth());
            employee.setMobileNumber(employeeRequestDTO.getMobileNumber());
            Employee saved = employeeRepository.save(employee);
            return "Employee has been updated.";
        }
        return "Employee has not been updated.";
    }

    @Override
    public String deleteEmployeeById(Long id) {
        Optional<Employee> byId = employeeRepository.findById(id);
        if(byId.isPresent()){
            employeeRepository.deleteById(id);
            return "Employee with the id " + id +" has been deleted.";
        }
        return "Employee with the id " + id +" is not present. ";
    }

}
