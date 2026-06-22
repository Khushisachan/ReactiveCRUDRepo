package com.example.reactive.service;


import com.example.reactive.entity.Employee;
import com.example.reactive.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
     EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void saveEmployee(){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("khushi");

       when(employeeRepository.save(any(Employee.class))).thenReturn(Mono.just(employee));

        StepVerifier.create(employeeService.saveEmployee(employee)).expectNext(employee).verifyComplete();

    }

    @Test
    public void getEmployee(){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("khushi");

        when(employeeRepository.findById(1L)).thenReturn(Mono.just(employee));
        StepVerifier.create(employeeService.getEmployee(1L)).expectNext(employee).verifyComplete();
    }

    @Test
    public void getAllEmployee(){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("khushi");

        Employee employee1 = new Employee();
        employee1.setId(2L);
        employee1.setName("khushi sachan");

        when(employeeRepository.findAll()).thenReturn(Flux.just(employee,employee1));
        StepVerifier.create(employeeService.getAllEmployee()).expectNext(employee).expectNext(employee1).verifyComplete();
    }
}
