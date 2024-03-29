package ado.rush.university.service.impl;

import ado.rush.university.entity.Trainer;
import ado.rush.university.repository.UserRepository;
import ado.rush.university.service.ITrainerService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TrainerService  implements ITrainerService {

    public final UserRepository<Trainer> userRepository;

    public TrainerService(UserRepository<Trainer> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Trainer findTrinerByLogin(String login) {
        return userRepository.getByLogin(login)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("user %s not found",login)));
    }
}
