package ma.montaxi.montaxiRestfulApi.daos;

import ma.montaxi.montaxiRestfulApi.entities.Reservation;
import ma.montaxi.montaxiRestfulApi.settings.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> getReservationsByTripIdAndStatus(Long tripId, ReservationStatus status);
}
