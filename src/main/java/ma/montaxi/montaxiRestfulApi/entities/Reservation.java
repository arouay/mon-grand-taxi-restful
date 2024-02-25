package ma.montaxi.montaxiRestfulApi.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.montaxi.montaxiRestfulApi.settings.enums.ReservationStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Trip trip;

    private LocalDateTime reservedAt;
    private ReservationStatus status;

    public Reservation(Passenger passenger, Trip trip, LocalDateTime reservedAt, ReservationStatus status) {
        this.passenger = passenger;
        this.trip = trip;
        this.reservedAt = reservedAt;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Long getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Trip getTrip() {
        return trip;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
