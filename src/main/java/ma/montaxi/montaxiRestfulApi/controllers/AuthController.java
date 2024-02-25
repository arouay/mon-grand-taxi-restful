package ma.montaxi.montaxiRestfulApi.controllers;

import ma.montaxi.montaxiRestfulApi.dtos.UserDto;
import ma.montaxi.montaxiRestfulApi.exceptions.HandledException;
import ma.montaxi.montaxiRestfulApi.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/")
public class AuthController {
    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("token")
    public Map<String, String> jwtToken (String username, String password) {
        return this.authService.jwtToken(username, password);
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(this.authService.authenticate(userDto));
        } catch (HandledException e) {
            return e.handle();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
