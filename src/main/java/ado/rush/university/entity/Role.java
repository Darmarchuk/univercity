package ado.rush.university.entity;

import java.util.Set;

public interface Role {
    public boolean includes(Role role);

    static Set<Role> roots(){
        return Set.of(SiteRole.ADMIN);
    }

}
