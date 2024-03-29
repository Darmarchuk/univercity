package ado.rush.university.service.impl;

import ado.rush.university.dto.department.DepartmentDto;
import ado.rush.university.entity.Department;
import ado.rush.university.entity.Trainer;
import ado.rush.university.mappers.department.DepartmentMapper;
import ado.rush.university.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;



class DepartmentServiceTest {

    @Mock
    DepartmentRepository repository;

    @Captor
    ArgumentCaptor<Department> departmentCaptor;



    DepartmentService underTest;


    @Spy
    DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest= new DepartmentService(repository,departmentMapper);
    }

    @Test
    void createdCustomerStoredInRepo() {
        DepartmentDto deptartmentDto = DepartmentDto.builder()
                .name("TestDepartment")
                .build();
        given(repository.findByName(deptartmentDto.getName()) ).willReturn(Optional.empty());

        underTest.create(deptartmentDto);
        then(repository).should().save(departmentCaptor.capture() );
        Department deptArgCapture = departmentCaptor.getValue();
        assertThat( deptArgCapture ).usingRecursiveComparison().isEqualTo( departmentMapper.toEntity(deptartmentDto) );
    }

    @Test
    @Disabled
    void createdCustomerThrowsException() {
        DepartmentDto deptartmentDto = DepartmentDto.builder()
                .name("TestDepartment")
                .trainerList(anyList())
                .build();
        Department dept = departmentMapper.toEntity(deptartmentDto);

        given(repository.findByName(deptartmentDto.getName()) ).willReturn(Optional.of(dept));

        assertThatThrownBy(() -> underTest.create(deptartmentDto))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(String.format(String.format("department  %s already exists ", deptartmentDto.getName() ), deptartmentDto.getName()));

    }
}