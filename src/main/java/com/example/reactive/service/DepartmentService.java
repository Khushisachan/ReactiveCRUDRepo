package com.example.reactive.service;

import com.example.reactive.entity.Department;
import com.example.reactive.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Mono<Department> saveDepartment( Department department){
        return departmentRepository.save(department);
    }

    public Mono<Department> getDepartment( Long id){
        return departmentRepository.findById(id);
    }

    public Flux<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }
}
