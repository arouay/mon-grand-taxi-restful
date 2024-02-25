package ma.montaxi.montaxiRestfulApi.controllers;

import ma.montaxi.montaxiRestfulApi.entities.Journey;
import ma.montaxi.montaxiRestfulApi.services.JourneyService;
import ma.montaxi.montaxiRestfulApi.settings.security.RoleEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journey/")
public class JourneyController {
    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @PostMapping(value = "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('" + RoleEnum.Constants.SCOPE_DRIVER_VALUE + "') or hasAuthority('" + RoleEnum.Constants.SCOPE_PASSENGER_VALUE + "')")
    public ResponseEntity<?> newJourney(@RequestBody Journey journey) {
        try {
            return ResponseEntity.ok(this.journeyService.saveJourney(journey));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
