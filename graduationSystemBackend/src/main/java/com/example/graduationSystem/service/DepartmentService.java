package com.example.graduationSystem.service;

import com.example.graduationSystem.dtos.CreateDepartmentDto;
import com.example.graduationSystem.dtos.DepartmentDto;

public interface DepartmentService {

    DepartmentDto addDepartment (CreateDepartmentDto department);

}
