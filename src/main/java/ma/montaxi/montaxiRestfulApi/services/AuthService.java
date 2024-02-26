package ma.montaxi.montaxiRestfulApi.services;

import lombok.extern.slf4j.Slf4j;
import ma.montaxi.montaxiRestfulApi.daos.IDriverRepository;
import ma.montaxi.montaxiRestfulApi.daos.IPassengerRepository;
import ma.montaxi.montaxiRestfulApi.daos.IRoleRepository;
import ma.montaxi.montaxiRestfulApi.daos.IUserRepository;
import ma.montaxi.montaxiRestfulApi.dtos.UserDto;
import ma.montaxi.montaxiRestfulApi.entities.Driver;
import ma.montaxi.montaxiRestfulApi.entities.Passenger;
import ma.montaxi.montaxiRestfulApi.entities.Role;
import ma.montaxi.montaxiRestfulApi.entities.User;
import ma.montaxi.montaxiRestfulApi.exceptions.InvalidUserException;
import ma.montaxi.montaxiRestfulApi.settings.constants.CarSeats;
import ma.montaxi.montaxiRestfulApi.settings.security.RoleEnum;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final IUserRepository userRepository;
    private final IDriverRepository driverRepository;
    private final IPassengerRepository passengerRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository roleRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, IUserRepository userRepository, IDriverRepository driverRepository, IPassengerRepository passengerRepository, PasswordEncoder passwordEncoder, IRoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.passengerRepository = passengerRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Map<String, String> jwtToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        Map<String, String> idToken = new HashMap<>();
        Instant now = Instant.now();

        String scope = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet
                .builder()
                .subject(authentication.getName())
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                .issuer("authentication-service")
                .claim("scope", scope)
                .build();

        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("accessToken", jwtAccessToken);

        return idToken;
    }

    public User authenticate(UserDto userDto) {
        if (this.userRepository.existsByEmail(userDto.getUsername())) {
            throw new InvalidUserException("Username is already taken");
        }

        Optional<Role> userRole = this.roleRepository.findByRole(
                userDto.getIsDriver() ? RoleEnum.DRIVER : RoleEnum.PASSENGER
        );

        Collection<Role> roles = new ArrayList<>();

        if (userRole.isEmpty()) {
            var role = Role.builder()
                    .role(userDto.getIsDriver() ? RoleEnum.DRIVER : RoleEnum.PASSENGER)
                    .build();

            log.info("****** No Role found | New Role created with role name : " + role.getRole().getName() + " ******");
            roles.add(this.roleRepository.save(role));
        } else {
            roles.add(userRole.get());
        }

        User user;

        if (userDto.getIsDriver()) {
            if (userDto.getCarRegistrationNumber().isEmpty()) {
                throw new InvalidUserException("Cannot create a Driver Account without a car registration number");
            }
            if (userDto.getCarCapacity() < CarSeats.MIN_CAPACITY) {
                userDto.setCarCapacity(CarSeats.DEFAULT_CAPACITY);
                log.info("****** Invalid Car capacity number given Default value is saved ******");
            }
            user = new Driver(
                   userDto.getFirstname(),
                   userDto.getLastname(),
                   userDto.getLocationLong(),
                   userDto.getLocationLat(),
                   userDto.getUsername(),
                   userDto.getPhoneNumber(),
                   passwordEncoder.encode(userDto.getPassword()),
                   roles,
                   false,
                   userDto.getCarRegistrationNumber(),
                   userDto.getCarCapacity()
            );
            return this.driverRepository.save((Driver) user);
        } else {
            user = new Passenger(
                    userDto.getFirstname(),
                    userDto.getLastname(),
                    userDto.getLocationLong(),
                    userDto.getLocationLat(),
                    userDto.getUsername(),
                    userDto.getPhoneNumber(),
                    passwordEncoder.encode(userDto.getPassword()),
                    roles,
                    false
            );
            return this.passengerRepository.save((Passenger) user);
        }
    }
}
