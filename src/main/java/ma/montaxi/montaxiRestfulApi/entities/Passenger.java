package ma.montaxi.montaxiRestfulApi.entities;

import javax.persistence.Entity;
import java.util.Collection;

@Entity
public class Passenger extends User {
    private boolean isWaiting;

    public Passenger() {
        super();
    }

    public Passenger(String firstname, String lastname, Double locationLong, Double locationLat, String email, String phoneNumber, String password, Collection<Role> roles, boolean isWaiting) {
        super(firstname, lastname, locationLong, locationLat, email, phoneNumber, password, roles);
        this.isWaiting = isWaiting;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public void setWaiting(boolean waiting) {
        isWaiting = waiting;
    }
}
