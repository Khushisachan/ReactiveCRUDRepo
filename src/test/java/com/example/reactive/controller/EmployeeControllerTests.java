package com.example.reactive.controller;

import com.example.reactive.entity.Employee;
import com.example.reactive.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(EmployeeController.class)
public class EmployeeControllerTests {

    @Autowired
    WebTestClient client;

    @MockitoBean
    EmployeeService employeeService;


    @Test
    public void saveEmployee(){

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("khushi");

        when(employeeService.saveEmployee(any())).thenReturn(Mono.just(employee));

        client.post().uri("/employees").bodyValue(employee).exchange().expectStatus().isOk().expectBody(Employee.class).isEqualTo(employee);

    }

    @Test
    public void getEmployee(){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("khushi");

        when(employeeService.getEmployee(1L)).thenReturn(Mono.just(employee));

        client.get().uri("/employees/{id}",1L).exchange().expectStatus().isOk().expectBody(Employee.class).isEqualTo(employee);
    }

    @Test
    public void getAllEmployee(){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("khushi");

        Employee employee1 = new Employee();
        employee1.setId(2L);
        employee1.setName("khushi sachan");

        Employee employee2 = new Employee();
        employee2.setId(3L);
        employee2.setName(" sachan khushi");

        when(employeeService.getAllEmployee()).thenReturn(Flux.just(employee,employee1,employee2));

        client.get().uri("/employees/Allemployees").exchange().expectStatus().isOk().expectBodyList(Employee.class).hasSize(3).contains(employee,employee1,employee2);
    }
}
