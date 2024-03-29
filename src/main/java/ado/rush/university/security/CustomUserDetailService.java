package ado.rush.university.security;


import ado.rush.university.entity.UserBaseEntity;
import ado.rush.university.repository.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserBaseRepository repository;


    @Autowired
    public CustomUserDetailService(UserBaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserBaseEntity> baseUser = repository.findByLogin(username);
        if(baseUser.isPresent()) {
            List<GrantedAuthority> authList = baseUser.get().getSiteRoles().stream()
                    .map(u -> {
                        return new SimpleGrantedAuthority(u.getSiteRole().getName() + "_ROLE");
                    })
                    .collect(Collectors.toList());
            ;
            return new User(baseUser.get().getLogin(),
                    baseUser.get().getPassword(),
                    authList
            );
        }
        else throw  new UsernameNotFoundException("user not found :"+ username);

    }
}
