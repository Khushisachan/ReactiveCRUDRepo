package com.example.reactive.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("departments")
@Data
public class Department {

    @Id
    private Long id;
    private String name;
    private String code;
}
