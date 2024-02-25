package ma.montaxi.montaxiRestfulApi.daos;

import ma.montaxi.montaxiRestfulApi.entities.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IJourneyRepository extends JpaRepository<Journey, Long> {
    boolean existsByDeparturePlaceAndArrivalPlace(String departurePlace, String arrivalPlace);
}
