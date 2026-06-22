package com.example.reactive.controller;

import com.example.reactive.entity.Department;
import com.example.reactive.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public Mono<Department> saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }
    @GetMapping("/{id}")
     public Mono<Department> getDepartment(@PathVariable Long id){
        return departmentService.getDepartment(id);
     }

     @GetMapping("/AllDepartments")
    public Flux<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
     }
}
