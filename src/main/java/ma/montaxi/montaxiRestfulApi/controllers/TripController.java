package ma.montaxi.montaxiRestfulApi.controllers;

import ma.montaxi.montaxiRestfulApi.dtos.TripDto;
import ma.montaxi.montaxiRestfulApi.entities.Passenger;
import ma.montaxi.montaxiRestfulApi.entities.Trip;
import ma.montaxi.montaxiRestfulApi.exceptions.HandledException;
import ma.montaxi.montaxiRestfulApi.services.TripService;
import ma.montaxi.montaxiRestfulApi.settings.security.Roles;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/trip/")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping(value = "available")
    @PreAuthorize("hasAuthority('" + Roles.SCOPE_USER + "')")
    public List<Trip> listAvailableTrips() {
        return this.tripService.getAvailableTrips();
    }

    @PostMapping(value = "new")
    @PreAuthorize("hasAuthority('" + Roles.SCOPE_DRIVER + "')")
    public ResponseEntity<?> declareTrip (@RequestBody @Valid TripDto trip, Principal principal) {
        String username = principal.getName();

        try {
            return ResponseEntity.ok(this.tripService.declareTrip(trip, username));
        } catch (HandledException e) {
            return e.handle();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
