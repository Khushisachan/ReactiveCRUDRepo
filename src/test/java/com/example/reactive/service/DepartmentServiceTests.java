package com.example.reactive.service;

import com.example.reactive.entity.Department;
import com.example.reactive.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTests {

    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    DepartmentService departmentService;

    Department department;

    @BeforeEach
    void setUp(){
        department = new Department();
        department.setId(1L);
        department.setName("IT");
        department.setCode("IT-101");
    }

    @Test
    void saveDepartment(){

        when(departmentRepository.save(any(Department.class))).thenReturn(Mono.just(department));

        StepVerifier.create(departmentService.saveDepartment(department)).expectNext(department).verifyComplete();
    }

    @Test
    void getDepartment(){

        when(departmentRepository.findById(1L)).thenReturn(Mono.just(department));

        StepVerifier.create(departmentService.getDepartment(1L)).expectNext(department).verifyComplete();
    }

    @Test
    void getAllDepartment(){

        Department department1 = new Department();
        department1.setId(2L);
        department1.setName("HR");
        department1.setCode("HR-102");

         when(departmentRepository.findAll()).thenReturn(Flux.just(department,department1));

         StepVerifier.create(departmentService.getAllDepartment()).expectNext(department).expectNext(department1);
    }
}
