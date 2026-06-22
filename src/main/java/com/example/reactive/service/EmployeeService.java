package com.example.reactive.service;

import com.example.reactive.entity.Employee;
import com.example.reactive.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Mono<Employee> saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Mono<Employee> getEmployee(Long id){
        return employeeRepository.findById(id);
    }

    public Flux<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }
}
