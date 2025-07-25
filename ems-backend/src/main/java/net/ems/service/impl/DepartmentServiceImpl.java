package net.ems.service.impl;

import lombok.AllArgsConstructor;
import net.ems.dto.DepartmentDto;
import net.ems.entity.Department;
import net.ems.exception.ResourceNotFoundException;
import net.ems.mapper.DepartmentMapper;
import net.ems.repository.DepartmentRepository;
import net.ems.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentDto createDepartment(DepartmentDto departmentDto){
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment =departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    public DepartmentDto getDepartmentById(Long departmentId){
     Department department = departmentRepository.findById(departmentId).orElseThrow( () -> new ResourceNotFoundException("Department is not exists with a given id : "+departmentId));
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    public List<DepartmentDto> getAllDepartments(){
       List<Department> departments =  departmentRepository.findAll();
       return departments.stream().map(DepartmentMapper::mapToDepartmentDto).collect(Collectors.toList());
    }

    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment){
       Department department = departmentRepository.findById(departmentId).orElseThrow(
                () ->new ResourceNotFoundException("Department Not exists with a given id : "+departmentId)
        );
       department.setDepartmentName(updatedDepartment.getDepartmentName());
       department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());

       Department savedDepartment = departmentRepository.save(department);
       return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    public void deleteDepartment(Long departmentId){
        departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department not exists with a given id : "+departmentId)
        );
        departmentRepository.deleteById(departmentId);
    }
}
