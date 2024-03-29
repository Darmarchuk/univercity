package ado.rush.university.entity;


import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

@Getter

public enum UserType {
    STUDENT,
    TRAINER,
    DEAN;


    @Component("UserType")
    static class UserTypeRoles {
        public static UserType STUDENT = UserType.STUDENT;
        public static UserType TRAINER = UserType.TRAINER;
        public static UserType DEAN = UserType.DEAN;
    }

}
