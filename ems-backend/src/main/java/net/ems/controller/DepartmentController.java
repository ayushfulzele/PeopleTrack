package net.ems.controller;

import lombok.AllArgsConstructor;
import net.ems.dto.DepartmentDto;
import net.ems.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    //Build Create or add Department Rest API
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
       DepartmentDto department = departmentService.createDepartment(departmentDto);
       return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId){
      DepartmentDto departmentDto=  departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(departmentDto);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        List<DepartmentDto> departments= departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(
            @PathVariable("id") Long departmentId,
            @RequestBody DepartmentDto updateDepartment){
        DepartmentDto departmentDto = departmentService.updateDepartment(departmentId, updateDepartment);
        return ResponseEntity.ok(departmentDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return  ResponseEntity.ok("Department deleted successfully");
    }
}
