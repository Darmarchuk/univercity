package ado.rush.university.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SiteRoleTest {

    @Test
    void getByName() {
        SiteRole role = SiteRole.getByName("viewer");
        assertThat(role).isEqualTo(SiteRole.VIEWER);

    }
}