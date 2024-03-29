package ado.rush.university.entity;


import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Getter
public enum SiteRole implements Role{
    VIEWER("viewer"),
    EDITOR("editor"),
    REPORTER("reporter"),
    ADMIN("admin");

    private final Set<Role> roleNames=new HashSet<>();
    private String name;

    private static final  Map<String,SiteRole> ROLE_MAP ;


    static {
        EDITOR.roleNames.add(VIEWER);
        REPORTER.roleNames.addAll(List.of(VIEWER,EDITOR));
        ADMIN.roleNames.addAll(List.of(VIEWER,EDITOR,REPORTER ));
        ROLE_MAP = Arrays.stream(SiteRole.values()).sequential().collect(toMap(Enum::name , Function.identity() ));
    }

    SiteRole(String name) {
        this.name=name;
    }

    public boolean includes(Role role){
        return  this.equals(role) ||  roleNames.stream().anyMatch(r -> r.includes( role));
    }


    @Override
    public String toString() {
        return roleNames.stream().map(s -> { return  (SiteRole)s;} ).map(c-> c.name).collect(Collectors.joining());
    }

    public static SiteRole getByName(String enumName){
        return  ROLE_MAP.get( enumName.toUpperCase());
    }
}