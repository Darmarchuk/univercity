package ado.rush.university.service.impl;

import ado.rush.university.dto.department.DepartmentDto;
import ado.rush.university.entity.Department;
import ado.rush.university.mappers.department.DepartmentMapper;
import ado.rush.university.repository.DepartmentRepository;
import ado.rush.university.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcOperationsExtensionsKt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DepartmentService implements IDepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Transactional
    @Override
    public DepartmentDto create(DepartmentDto dto) {
        if (departmentRepository.findByName(dto.getName()).isPresent())
            throw new IllegalStateException(String.format("department  %s already exists ", dto.getName()));


        return departmentMapper.toDto(departmentRepository.save(departmentMapper.toEntity(dto)));
    }

    @Override
    public List<String> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(Department::getName)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public DepartmentDto update(Long depId, DepartmentDto dto) {
        Optional<Department> dep = departmentRepository.findById(depId);

        if (dep.isEmpty()) {
//            throw  new IllegalArgumentException(String.format("department %s not found",depId));
            return departmentMapper.toDto(departmentRepository.saveAndFlush(departmentMapper.toEntity(dto)));
        }
        else {
            departmentMapper.updateDepartmentFromDto(dto, dep.get());
            return departmentMapper.toDto(departmentRepository.findById(depId).get());
        }

    }

    @Override
    public Page<Department> getAllDepartments(PageRequest pageRequest) {
        int pageSize = pageRequest.getPageSize();
        int pageNo = pageRequest.getPageNumber();
        int startFrom = pageNo * pageSize;

        Page<Department> depts = departmentRepository.findAll(pageRequest);
        return depts;


//        return new PageImpl<Department>(Collections.emptyList() , PageRequest.of(pageNo, pageSize),departmentRepository.count() );
    }

    @Override
    public DepartmentDto getDepartmentById(Long depId) {
        return departmentMapper.toDto(departmentRepository.getReferenceById(depId));
    }

    @Override
    public void delete(Long depId) {
        System.out.println("delete");
    }

}
