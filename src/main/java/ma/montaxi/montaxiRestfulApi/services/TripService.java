package ma.montaxi.montaxiRestfulApi.services;

import lombok.extern.slf4j.Slf4j;
import ma.montaxi.montaxiRestfulApi.daos.IJourneyRepository;
import ma.montaxi.montaxiRestfulApi.daos.IReservationRepository;
import ma.montaxi.montaxiRestfulApi.daos.ITripRepository;
import ma.montaxi.montaxiRestfulApi.daos.IUserRepository;
import ma.montaxi.montaxiRestfulApi.dtos.TripDto;
import ma.montaxi.montaxiRestfulApi.entities.*;
import ma.montaxi.montaxiRestfulApi.exceptions.IncorrectMomentException;
import ma.montaxi.montaxiRestfulApi.exceptions.JourneyNotFound;
import ma.montaxi.montaxiRestfulApi.helpers.PeriodIntervalChecker;
import ma.montaxi.montaxiRestfulApi.settings.constants.CarSeats;
import ma.montaxi.montaxiRestfulApi.settings.enums.ReservationStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class TripService {
    private final ITripRepository tripRepository;
    private final IReservationRepository reservationRepository;
    private final IUserRepository userRepository;
    private final IJourneyRepository journeyRepository;

    public TripService(ITripRepository tripRepository, IReservationRepository reservationRepository, IUserRepository userRepository, IJourneyRepository journeyRepository) {
        this.reservationRepository = reservationRepository;
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
        this.journeyRepository = journeyRepository;
    }

    public List<Trip> getAvailableTrips() {
        // available trip = departureTime + time between driver and passenger > now && freePlaces left >= 1
        List<Trip> trips = this.tripRepository.findAllByFreeSeatsLeftIsGreaterThanAndDepartureTimeIsBefore(
        0,
            LocalDateTime.now() // TODO should consider time between driver and passenger
        );

        return trips;
    }

    public List<Passenger> getWaitingPassengersInTrip(Long tripId) {
        List<Passenger> waitingPassengers = new ArrayList<>();
        ArrayList<Reservation> _reservationsInTrip
                = (ArrayList<Reservation>) this.reservationRepository.getReservationsByTripIdAndStatus(
                        tripId,
                        ReservationStatus.CREATED
                );

        _reservationsInTrip.forEach(reservation -> {
            if (reservation.getPassenger().isWaiting()) {
                waitingPassengers.add(reservation.getPassenger());
            }
        });

        return waitingPassengers;
    }

    public Trip declareTrip (TripDto tripDto, String username) {
        if (tripDto.getDepartureTime().isBefore(LocalDateTime.now())
            || tripDto.getArrivalTime().isBefore(tripDto.getDepartureTime())
        ) {
            throw new IncorrectMomentException(tripDto.getDepartureTime(), tripDto.getArrivalTime());
        }
        User user = this.userRepository.findByEmail(username);
        if (user instanceof Driver driver) {
            List<Trip> trips = this.tripRepository.findByDriver(driver);

            for (Trip currentTrip : trips) {
                if (PeriodIntervalChecker.isIntervalsOverlapped(
                        tripDto.getDepartureTime(),
                        tripDto.getArrivalTime(),
                        currentTrip.getDepartureTime(),
                        currentTrip.getArrivalTime()
                )) {
                    throw new IncorrectMomentException(tripDto.getDepartureTime());
                }
            }

            if (tripDto.getFreeSeatsLeft() > CarSeats.MAX_CAPACITY || tripDto.getFreeSeatsLeft() < CarSeats.MIN_CAPACITY) {
                log.info("****** Invalid free seats number provided | default 6 seats stored ******");
                tripDto.setFreeSeatsLeft(CarSeats.DEFAULT_CAPACITY);
            }

            Optional<Journey> journey = this.journeyRepository.findById(tripDto.getJourneyId());
            if (journey.isEmpty()) {
                throw new JourneyNotFound("Creating a trip with a none exising journey");
            }
            Trip trip = new Trip(driver, journey.get(), tripDto.getDepartureTime(), tripDto.getArrivalTime(), tripDto.getFreeSeatsLeft());
            return this.tripRepository.save(trip);
        } else {
            throw new RuntimeException("Executing unauthorized code");
        }
    }
}
