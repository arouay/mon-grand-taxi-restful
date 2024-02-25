package ma.montaxi.montaxiRestfulApi.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class UserDto {
    @NotNull
    @Email
    private String username;
    @NotNull
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private Double locationLat;
    private Double locationLong;
    @NotNull
    private Boolean isDriver;
    private Boolean isWaiting;
    private Boolean isLive;
    private String carRegistrationNumber;
    private Integer carCapacity;
}
