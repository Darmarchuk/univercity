package ado.rush.university.repository;


import ado.rush.university.entity.UserBaseTypeDto;
import ado.rush.university.entity.UserBaseEntity;
import groovy.transform.NamedParam;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBaseRepository extends JpaRepository<UserBaseEntity,Long> {

@EntityGraph(value = "UserBaseEntity.siteRoles")
    Optional<UserBaseEntity> findByLogin(String login);
    @Query(nativeQuery = true)
    Optional<UserBaseTypeDto> findUserTypeByLogin(@NamedParam("login") String login);


}
