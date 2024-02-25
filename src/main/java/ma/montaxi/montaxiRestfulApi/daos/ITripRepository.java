package ma.montaxi.montaxiRestfulApi.daos;

import ma.montaxi.montaxiRestfulApi.entities.Driver;
import ma.montaxi.montaxiRestfulApi.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface ITripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByFreeSeatsLeftIsGreaterThanAndDepartureTimeIsBefore(Integer seat, LocalDateTime time);
    List<Trip> findByDriver(Driver driver);
}
