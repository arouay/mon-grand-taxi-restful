package ma.montaxi.montaxiRestfulApi.daos;

import ma.montaxi.montaxiRestfulApi.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger findPassengerByEmail(String email);
}
