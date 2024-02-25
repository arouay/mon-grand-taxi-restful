package ma.montaxi.montaxiRestfulApi.controllers;

import ma.montaxi.montaxiRestfulApi.dtos.ReservationDto;
import ma.montaxi.montaxiRestfulApi.entities.Passenger;
import ma.montaxi.montaxiRestfulApi.exceptions.HandledException;
import ma.montaxi.montaxiRestfulApi.services.ReservationService;
import ma.montaxi.montaxiRestfulApi.services.TripService;
import ma.montaxi.montaxiRestfulApi.settings.security.Roles;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/reservation/")
public class ReservationController {
    private final ReservationService reservationService;
    private final TripService tripService;

    public ReservationController(ReservationService reservationService, TripService tripService) {
        this.reservationService = reservationService;
        this.tripService = tripService;
    }

    @GetMapping(value = "waitingPassengers/{tripId}")
    @PreAuthorize("hasAuthority('" + Roles.SCOPE_DRIVER + "')")
    public List<Passenger> listWaitingPassengersInTrip(@PathVariable Long tripId) {
        return this.tripService.getWaitingPassengersInTrip(tripId);
    }

    @PostMapping("seat/new")
    @PreAuthorize("hasAuthority('" + Roles.SCOPE_PASSENGER + "')")
    public ResponseEntity<?> reserveYourSeat(@RequestBody ReservationDto reservationDto, Principal principal) {
        String username = principal.getName();

        try {
            return ResponseEntity.ok(this.reservationService.reserveYourSeat(reservationDto, username));
        } catch (HandledException e) {
            return e.handle();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
