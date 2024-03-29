package ado.rush.university.security;

import ado.rush.university.entity.UserBaseTypeDto;
import ado.rush.university.entity.SiteRole;
import ado.rush.university.entity.UserBaseEntity;
import ado.rush.university.entity.UserBaseRoles;
import ado.rush.university.entity.UserType;
import ado.rush.university.repository.UserBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service("RoleService")
@RequiredArgsConstructor
public class RoleService {

    private final UserBaseRepository repository;
    private final SecurityConfig securityConfig;

    @Transactional
    public boolean hasAnySiteRole(SiteRole... siteRoles) {
        Optional<UserBaseEntity> userOptinal = repository.findByLogin(getCurrentUser());
        if (userOptinal.isPresent()) {
            UserBaseEntity user = userOptinal.get();

            long result = user.getSiteRoles().stream()
                    .map(UserBaseRoles::getSiteRole)
                    .filter(Arrays.stream(siteRoles).toList()::contains)
                    .count();
            if (result > 0)
                return true;

        }
        return false;
    }


    @Transactional
    public boolean hasAnySiteRole(UserType... siteRoles) {
        Optional<UserBaseTypeDto> userOptinal = repository.findUserTypeByLogin(getCurrentUser());
        if (userOptinal.isPresent()) {
            UserType userType = UserType.values()[ userOptinal.get().getUserType()-1];

            Optional<UserType> res = Arrays.stream(siteRoles).filter(u -> u.equals(userType)).findAny();
            if (res.isPresent())
                return true;
        }
        return false;
    }


    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return null;
    }

    public Object getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object user = authentication.getPrincipal();
            return user;
        }
        return null;
    }

}
