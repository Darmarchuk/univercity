package ado.rush.university.repository;

import ado.rush.university.entity.UserBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository<T extends UserBaseEntity> extends JpaRepository<T,Long > {

    T findByLogin(String username);
    Optional<T> getByLogin(String username);

}
