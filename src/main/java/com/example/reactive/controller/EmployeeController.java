package com.example.reactive.controller;

import com.example.reactive.entity.Employee;
import com.example.reactive.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public Mono<Employee> saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public Mono<Employee> getEmployee(@PathVariable Long id){
        return employeeService.getEmployee(id);
    }

    @GetMapping("/Allemployees")
    public Flux<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PutMapping("/update/{id}")
    public Mono<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable Long id){
        return employeeService.updateEmployee(employee,id);
    }
}
