package ado.rush.university.service;

import ado.rush.university.dto.department.DepartmentDto;
import ado.rush.university.entity.Department;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDepartmentService {
    DepartmentDto create( DepartmentDto dto);

    List<String> getAllDepartments();

    DepartmentDto update(Long depId,DepartmentDto dto);

    Page<Department> getAllDepartments(PageRequest of);

    DepartmentDto getDepartmentById(Long depId);

    void delete(Long depId);
}

