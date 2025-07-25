package net.ems.service.impl;

import lombok.AllArgsConstructor;
import net.ems.dto.EmployeeDto;
import net.ems.entity.Department;
import net.ems.entity.Employee;
import net.ems.exception.ResourceNotFoundException;
import net.ems.mapper.EmployeeMapper;
import net.ems.repository.DepartmentRepository;
import net.ems.repository.EmployeeRepository;
import net.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow( () ->
                        new ResourceNotFoundException("Department is not exists with the id : "+employeeDto.getDepartmentId()));
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    public EmployeeDto getEmployeeById(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not Exists with give id : "+employeeId));
       return EmployeeMapper.mapToEmployeeDto(employee);
    }

    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow( () -> new ResourceNotFoundException("Employee is exists with give id : "+employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
                .orElseThrow( () ->
                        new ResourceNotFoundException("Department is not exists with the id : "+updatedEmployee.getDepartmentId()));
        employee.setDepartment(department);

        Employee updatedEmployeeObj =employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);

    }

    public void deleteEmployee(Long employeeId){
       Employee employee= employeeRepository.findById(employeeId).orElseThrow(
               () -> new ResourceNotFoundException("Employee is not exists with given id: "+employeeId)
       );
        employeeRepository.deleteById(employeeId);

    }
}
