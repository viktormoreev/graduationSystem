package com.example.graduationSystem.service;

import com.example.graduationSystem.dtos.CreateDepartmentDto;
import com.example.graduationSystem.dtos.DepartmentDto;
import com.example.graduationSystem.entity.Department;

public interface DepartmentService {

    DepartmentDto addDepartment (CreateDepartmentDto department);

    Department fetchDepartmentById (Long id);

}
