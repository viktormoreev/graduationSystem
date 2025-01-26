package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.CreateDepartmentDto;
import com.example.graduationSystem.dtos.DepartmentDto;
import com.example.graduationSystem.entity.Department;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.DepartmentRepository;
import com.example.graduationSystem.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto addDepartment(CreateDepartmentDto departmentDto) {
        final Department department = Department.builder()
                .name(departmentDto.getName())
                .professors(List.of())
                .build();
        return entityMapper.mapToDepartmentDto(departmentRepository.save(department));
    }

    @Override
    public Department fetchDepartmentById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(!departmentOptional.isPresent()){
            throw new EntityNotFoundException("Department with Id " + id + " is not found");
        }
        return departmentOptional.get();
    }
}
