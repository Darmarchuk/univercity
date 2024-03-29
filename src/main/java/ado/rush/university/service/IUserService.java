package ado.rush.university.service;

import ado.rush.university.dto.StudentDetailsDto;
import ado.rush.university.dto.UserBaseEntityDto;
import ado.rush.university.exception.ResourceNotFoundException;

import java.util.List;

public interface IUserService {
    UserBaseEntityDto findById(Long id ) throws ResourceNotFoundException;
    List<StudentDetailsDto> findAll();
    UserBaseEntityDto save(UserBaseEntityDto userDetailsDto);

    boolean checkUserByLogin(String s);
}
