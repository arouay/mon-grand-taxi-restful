package ma.montaxi.montaxiRestfulApi.daos;

import ma.montaxi.montaxiRestfulApi.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDriverRepository extends JpaRepository<Driver, Long> {
}
