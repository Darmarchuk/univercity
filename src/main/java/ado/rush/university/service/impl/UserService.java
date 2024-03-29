package ado.rush.university.service.impl;

import ado.rush.university.dto.StudentDetailsDto;
import ado.rush.university.dto.UserBaseEntityDto;
import ado.rush.university.entity.*;
import ado.rush.university.exception.ResourceNotFoundException;
import ado.rush.university.mappers.DeanMapper;
import ado.rush.university.mappers.StudentMapper;
import ado.rush.university.mappers.TrainerMapper;
import ado.rush.university.mappers.UserMapper;
import ado.rush.university.mappers.userdetails.UserDetailsMapper;
import ado.rush.university.repository.UserBaseRepository;
import ado.rush.university.repository.UserRepository;
import ado.rush.university.service.IUserService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SimpleTimeZone;
import java.util.stream.Collectors;


@Service
public class UserService implements IUserService {
    private final EntityManager em;


    private final PasswordEncoder passwordEncoder;

    private final UserBaseRepository userRepository;
    private final UserRepository<Student> studentUserRepository;
    private final UserRepository<Trainer> trainerRepository;
    private final UserRepository<Dean> deanRepository;
    private final TrainerMapper trainerMapper;
    private  final UserDetailsMapper userDetailsMapper;
    private final DeanMapper deanMapper;

    @Autowired
    public UserService(EntityManager entityManager, PasswordEncoder passwordEncoder, UserBaseRepository userRepository, UserRepository<Student> studentUserRepository, UserRepository<Trainer> trainerRepository, UserRepository<Dean> deanRepository, TrainerMapper trainerMapper, UserDetailsMapper userDetailsMapper, DeanMapper deanMapper) {
        this.em = entityManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.studentUserRepository = studentUserRepository;
        this.trainerRepository = trainerRepository;
        this.deanRepository = deanRepository;
        this.trainerMapper = trainerMapper;
        this.userDetailsMapper = userDetailsMapper;
        this.deanMapper = deanMapper;
    }

    @Override
    public UserBaseEntityDto findById(Long id) {
        UserBaseEntity user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("user with id: %d not found", id)));
        return UserMapper.mapUserToUserDetailsDto(user);

    }

    @Override
    public List<StudentDetailsDto> findAll() {
        return null;
    }

    public boolean checkUserByLogin(String username) {
        return userRepository.findByLogin(username).isPresent();
    }


    @Override
    public UserBaseEntityDto save(UserBaseEntityDto studentDetailsDto) {

//        String jpql= "select a from StudentRoles a where a.siteRole= :role";
//        TypedQuery<StudentRoles> query = em.createQuery(jpql, StudentRoles.class );
//        query.setParameter("role", SiteRole.ADMIN);
//        List<StudentRoles> result = query.getResultList();

        switch (studentDetailsDto.getUserType()){


            case STUDENT ->{
            return saveStudent(studentDetailsDto);
            }
            case TRAINER -> {
                return saveTrainer(studentDetailsDto);
            }
            case DEAN -> {
                return saveDean(studentDetailsDto);
            }
            default -> throw new IllegalStateException("Illegal user role");

        }
    }

//        if (studentDetailsDto.getUserType() == UserType.STUDENT)


//        UserBaseEntity user = UserMapper.mapStudentDetailsDtoToEntity(studentDetailsDto);
//        UserBaseEntity finalUser = user;
//        List<UserBaseRoles> userRoles = studentDetailsDto.getRoles()
//                .stream()
//                .map(r -> UserBaseRoles.builder()
//                        .siteRole(SiteRole.getByName(r))
//                        .user(finalUser)
//                        .build())
//                .collect(Collectors.toList());
//        user.setSiteRoles(userRoles);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user = userRepository.save(user);
//        return UserMapper.mapUserToUserDetailsDto(user);



    public UserBaseEntityDto saveStudent(UserBaseEntityDto dto) {
        Student student = StudentMapper.mapStudentDetailsDtoToEntity(dto);
        Student finalUser = student;
        List<UserBaseRoles> userRoles = dto.getRoles()
                .stream()
                .map(r -> UserBaseRoles.builder()
                        .siteRole(SiteRole.getByName(r))
                        .user(finalUser)
                        .build())
                .collect(Collectors.toList());

        List<StudentRoles> studentRoles = dto.getStudentRoles()
                .stream()
                .map(r -> StudentRoles.builder()
                        .siteRole(SiteRole.getByName(r))
                        .user(finalUser)
                        .build()).toList();
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setStudentRoles(studentRoles);
        student.setSiteRoles(userRoles);
        student = studentUserRepository.save(student);

        return StudentMapper.mapStudentToUserDetailsDto(student);

    }

    public  UserBaseEntityDto saveTrainer(UserBaseEntityDto dto) {
        Trainer trainer = trainerMapper.mapToEntity(dto);
        CustomUserDetails userDetails = userDetailsMapper.toEntity(dto);
        userDetails.setUser(trainer);
        trainer.setUserDetails(userDetails);
        Trainer finalTrainer = trainer;
        trainer.setSiteRoles( dto.getRoles().stream()
                .map(r -> UserBaseRoles.builder()
                        .siteRole(SiteRole.getByName(r))
                        .user(finalTrainer)
                        .build()).toList());

        trainer.setPassword(passwordEncoder.encode(dto.getPassword()));

        trainer = trainerRepository.save(trainer);

        return trainerMapper.toDto(trainer);
    }

    public  UserBaseEntityDto saveDean(UserBaseEntityDto dto) {
        Dean dean = deanMapper.mapToEntity(dto);
        CustomUserDetails userDetails = userDetailsMapper.toEntity(dto);
        userDetails.setUser(dean);
        Dean finalDean = dean;
        dean.setSiteRoles( dto.getRoles().stream()
                .map(r -> UserBaseRoles.builder()
                        .siteRole(SiteRole.getByName(r))
                        .user(finalDean)
                        .build()).toList());
        dean.setUserDetails( userDetails);
        dean.setPassword(passwordEncoder.encode(dto.getPassword()));
        dean = deanRepository.save(dean);
        return deanMapper.toDto(dean);
    }
}
