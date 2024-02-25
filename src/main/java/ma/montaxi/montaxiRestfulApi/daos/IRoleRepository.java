package ma.montaxi.montaxiRestfulApi.daos;

import ma.montaxi.montaxiRestfulApi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
}
