package ma.montaxi.montaxiRestfulApi.settings.converters;

import ma.montaxi.montaxiRestfulApi.settings.security.RoleEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleEnumConverter implements AttributeConverter<RoleEnum, String> {
    @Override
    public String convertToDatabaseColumn(RoleEnum roleEnum) {
        return roleEnum.getName();
    }

    @Override
    public RoleEnum convertToEntityAttribute(String roleName) {
        return RoleEnum.getByName(roleName);
    }
}