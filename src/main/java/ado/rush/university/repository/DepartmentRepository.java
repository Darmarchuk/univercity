package ado.rush.university.repository;

import ado.rush.university.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Optional<Department> findByName(String name);

    @Override
    Optional<Department> findById(Long id);

}
