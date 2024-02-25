package ma.montaxi.montaxiRestfulApi.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Trip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Driver driver;

    @ManyToOne(fetch = FetchType.EAGER)
    private Journey journey;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer freeSeatsLeft;

    public Trip(Driver driver, Journey journey, LocalDateTime departureTime, LocalDateTime arrivalTime, Integer freeSeatsLeft) {
        this.driver = driver;
        this.journey = journey;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.freeSeatsLeft = freeSeatsLeft;
    }

    public boolean decrementFreeSeatsNumber() {
        if (freeSeatsLeft < 1) {
            return false;
        }

        freeSeatsLeft--;
        return true;
    }
    public Long getId() {
        return id;
    }

    public Driver getDriver() {
        return driver;
    }

    public Journey getJourney() {
        return journey;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public Integer getFreeSeatsLeft() {
        return freeSeatsLeft;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setFreeSeatsLeft(Integer freeSeatsLeft) {
        this.freeSeatsLeft = freeSeatsLeft;
    }
}
