package ado.rush.university.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UserTypeTest {

    private List<String> validRoles;
    @BeforeEach
    void preinit(){
        validRoles=List.of("STUDENT","TRAINER","DEAN");
    }


    @Test
    void TestEnumValues(){
        for (UserType type : UserType.values()){
          assertDoesNotThrow(()->
                  validRoles.contains(type.name())
          );
        }

    }

}