package ma.montaxi.montaxiRestfulApi.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private Double locationLong;
    private Double locationLat;
    @NotNull
    @Column(unique = true)
    private String email;
    private String phoneNumber;

    @NotNull
    private String password;

    public User(Collection<Role> roles) {
        this.roles = roles;
    }

    public User(String firstname, String lastname, Double locationLong, Double locationLat, String email, String phoneNumber, String password, Collection<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.locationLong = locationLong;
        this.locationLat = locationLat;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roles = roles;
    }

    @ManyToMany
    private Collection<Role> roles;

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Double getLocationLong() {
        return locationLong;
    }

    public Double getLocationLat() {
        return locationLat;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setLocationLong(Double locationLong) {
        this.locationLong = locationLong;
    }

    public void setLocationLat(Double locationLat) {
        this.locationLat = locationLat;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
