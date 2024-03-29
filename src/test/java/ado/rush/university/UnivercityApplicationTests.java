package ado.rush.university;

import ado.rush.university.entity.Role;
import ado.rush.university.entity.SiteRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
class UnivercityApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void TestRoleEnums() {

        var roots = Role.roots();
        var roles = SiteRole.values();

        assertDoesNotThrow(() ->
        {
            for (Role root : roots) {
                for (SiteRole sr : roles) {
                    root.includes(sr);
                }
            }

        });

    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    public void checRoleInclues(Role checkRole, Set<Role> roles, Boolean assertVal) {
        for (Role role : roles) {
            assertEquals(role.includes(checkRole), assertVal);
        }
    }


    public static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.arguments(
                        SiteRole.ADMIN,
                        Set.of(SiteRole.EDITOR,SiteRole.REPORTER),
                        false
                ),
                Arguments.arguments(
                        SiteRole.VIEWER,
                        Set.of(SiteRole.EDITOR,SiteRole.REPORTER,SiteRole.ADMIN),
                        true
                )
        );
    }

}
