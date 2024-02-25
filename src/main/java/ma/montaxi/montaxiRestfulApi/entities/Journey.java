package ma.montaxi.montaxiRestfulApi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Journey implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departurePlace;
    private String arrivalPlace;
    private Long price;

    public Long getId() {
        return id;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public Long getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
