package ma.montaxi.montaxiRestfulApi.services;

import lombok.extern.slf4j.Slf4j;
import ma.montaxi.montaxiRestfulApi.daos.IPassengerRepository;
import ma.montaxi.montaxiRestfulApi.daos.IReservationRepository;
import ma.montaxi.montaxiRestfulApi.daos.ITripRepository;
import ma.montaxi.montaxiRestfulApi.dtos.LocationDto;
import ma.montaxi.montaxiRestfulApi.dtos.ReservationDto;
import ma.montaxi.montaxiRestfulApi.entities.Passenger;
import ma.montaxi.montaxiRestfulApi.entities.Reservation;
import ma.montaxi.montaxiRestfulApi.entities.Trip;
import ma.montaxi.montaxiRestfulApi.exceptions.IncorrectMomentException;
import ma.montaxi.montaxiRestfulApi.exceptions.NoSeatsAvailable;
import ma.montaxi.montaxiRestfulApi.exceptions.OutdatedTripException;
import ma.montaxi.montaxiRestfulApi.helpers.LocationHelper;
import ma.montaxi.montaxiRestfulApi.settings.enums.ReservationStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ReservationService {
    private IReservationRepository reservationRepository;
    private IPassengerRepository passengerRepository;
    private ITripRepository tripRepository;

    public ReservationService(IReservationRepository reservationRepository, IPassengerRepository passengerRepository, ITripRepository tripRepository) {
        this.reservationRepository = reservationRepository;
        this.passengerRepository = passengerRepository;
        this.tripRepository = tripRepository;
    }

    public Reservation reserveYourSeat(ReservationDto reservationDto, String username) {
        Optional<Trip> trip = this.tripRepository.findById(reservationDto.getTripId());
        if (trip.isEmpty()) {
            throw new RuntimeException("Invalid trip provided");
        }
        if (!trip.get().decrementFreeSeatsNumber()) {
            throw new NoSeatsAvailable(trip.get().getId());
        }
        if (reservationDto.getReservedAt().isAfter(trip.get().getArrivalTime())) {
            throw new IncorrectMomentException(reservationDto.getReservedAt());
        }

        Passenger passenger = this.passengerRepository.findPassengerByEmail(username);

        Double passengerLongitude  = passenger.getLocationLong(),
                passengerLatitude = passenger.getLocationLat(),
                tripLongitude = trip.get().getDriver().getLocationLong(),
                tripLatitude = trip.get().getDriver().getLocationLat();

        LocationDto passengerLocation = LocationDto.builder()
                .latitude(passengerLatitude)
                .longitude(passengerLongitude)
                .build();
        LocationDto tripLocation = LocationDto.builder()
                .latitude(tripLatitude)
                .longitude(tripLongitude)
                .build();

        if (LocationHelper.getDistanceBetweenTwoLocationsInJourney(tripLocation, passengerLocation, trip.get().getJourney()) < 0) {
            throw new OutdatedTripException("It is too late to reserve a seat on this trip");
        }

        Reservation reservation = new Reservation(passenger, trip.get(), reservationDto.getReservedAt(), ReservationStatus.NEW);
        reservation.setStatus(ReservationStatus.NEW);

        this.tripRepository.save(trip.get());

        return this.reservationRepository.save(reservation);
    }
}
