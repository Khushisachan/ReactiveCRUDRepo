package com.example.reactive.controller;

import com.example.reactive.entity.Department;
import com.example.reactive.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.data.relational.core.sql.When;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(DepartmentController.class)
public class DepartmentControllerTests {

    @MockitoBean
    DepartmentService departmentService;

    @Autowired
    WebTestClient client;

    Department department;

    @BeforeEach
    void setUp(){
        department = new Department();
        department.setId(1L);
        department.setName("IT");
        department.setCode("IT-101");
    }


    @Test
    public void saveDepartment(){
        when(departmentService.saveDepartment(any())).thenReturn(Mono.just(department));
        client.post().uri("/departments").bodyValue(department).exchange().expectStatus().isOk().expectBody(Department.class).isEqualTo(department);

    }

    @Test
    public void getDepartment(){
        when(departmentService.getDepartment(1L)).thenReturn(Mono.just(department));
        client.get().uri("/departments/{id}",1L).exchange().expectStatus().isOk().expectBody(Department.class).isEqualTo(department);
    }

    @Test
    public void getAllDepartment(){

        Department department1 = new Department();
        department.setId(2L);
        department.setName("CS");
        department.setCode("CS-102");

        Department department2 = new Department();
        department.setId(3L);
        department.setName("CSE");
        department.setCode("CSE-103");
        when(departmentService.getAllDepartment()).thenReturn(Flux.just(department,department1,department2));
        client.get().uri("/departments/AllDepartments").exchange().expectStatus().isOk();
    }
}
