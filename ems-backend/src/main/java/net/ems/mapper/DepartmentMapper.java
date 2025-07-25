package net.ems.mapper;

import net.ems.dto.DepartmentDto;
import net.ems.dto.EmployeeDto;
import net.ems.entity.Department;

public class DepartmentMapper {

    //convert department jpa entity into department dto
   public static  DepartmentDto mapToDepartmentDto(Department department){
       return new DepartmentDto(
               department.getId(),
               department.getDepartmentName(),
               department.getDepartmentDescription()
       );
   }

   //convert departmentDto into  department jpa entity
    public static Department mapToDepartment(DepartmentDto departmentDto){
       return new Department(
               departmentDto.getId(),
               departmentDto.getDepartmentName(),
               departmentDto.getDepartmentDescription()
       );
    }
}

