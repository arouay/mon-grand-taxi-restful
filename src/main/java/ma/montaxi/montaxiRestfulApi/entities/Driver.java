package ma.montaxi.montaxiRestfulApi.entities;

import javax.persistence.Entity;
import java.util.Collection;

@Entity
public class Driver extends User {
    private boolean isLive;
    private String carRegistrationNumber;
    private Integer carCapacity;

    public Driver() {
        super();
    }

    public Driver(String firstname, String lastname, Double locationLong, Double locationLat, String email, String phoneNumber, String password, Collection<Role> roles, boolean isLive, String carRegistrationNumber, Integer carCapacity) {
        super(firstname, lastname, locationLong, locationLat, email, phoneNumber, password, roles);
        this.isLive = isLive;
        this.carRegistrationNumber = carRegistrationNumber;
        this.carCapacity = carCapacity;
    }

    public boolean isLive() {
        return isLive;
    }

    public String getCarRegistrationNumber() {
        return carRegistrationNumber;
    }

    public Integer getCarCapacity() {
        return carCapacity;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void setCarRegistrationNumber(String carRegistrationNumber) {
        this.carRegistrationNumber = carRegistrationNumber;
    }

    public void setCarCapacity(Integer carCapacity) {
        this.carCapacity = carCapacity;
    }
}
