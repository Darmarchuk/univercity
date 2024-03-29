package ado.rush.university.entity.converter;

import ado.rush.university.entity.SiteRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.SneakyThrows;

@Converter(autoApply = true)
public class SiteRoleConverter  implements AttributeConverter<SiteRole,String> {
    @Override
    public String convertToDatabaseColumn(SiteRole siteRole) {
        if(siteRole == null)
            return null;
        return siteRole.name().toUpperCase();
    }

    @SneakyThrows
    @Override
    public SiteRole convertToEntityAttribute(String s) {
        if(s==null) return  null;

        SiteRole role = SiteRole.getByName(s.toUpperCase());
        if(role==null)
            throw new IllegalAccessException("argument for enum not found");
        return role;

    }
}
