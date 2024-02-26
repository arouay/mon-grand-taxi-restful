package ma.montaxi.montaxiRestfulApi.settings.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static ma.montaxi.montaxiRestfulApi.settings.security.RoleEnum.Constants.*;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    USER(USER_VALUE),
    ADMIN(ADMIN_VALUE),
    PASSENGER(PASSENGER_VALUE),
    DRIVER(DRIVER_VALUE);

    private final String name;

    public static RoleEnum getByName(String roleName) {
        if (roleName == null) {
            throw new IllegalArgumentException();
        }

        return Arrays.stream(values())
                .filter(roleEnum -> roleEnum.getName().equals(roleName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static final class Constants {
        public static final String USER_VALUE = "USER";
        public static final String ADMIN_VALUE = "ADMIN";
        public static final String PASSENGER_VALUE = "PASSENGER";
        public static final String DRIVER_VALUE = "DRIVER";
        public static final String scope_prefix = "SCOPE_";
        public static final String SCOPE_USER_VALUE = scope_prefix + USER_VALUE;
        public static final String SCOPE_ADMIN_VALUE = scope_prefix + ADMIN_VALUE;
        public static final String SCOPE_PASSENGER_VALUE = scope_prefix + DRIVER_VALUE;
        public static final String SCOPE_DRIVER_VALUE = scope_prefix + DRIVER_VALUE;
    }
}
