package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Ekaterina on 03.03.2015.
 */
@javax.persistence.SequenceGenerator(name = "id_addr", sequenceName = "id_addr")
@Entity
@Table(name = "address")
public class Address {
    private Integer addressId;
    private String street;
    private String house;
    private String flat;
    private List<Subscriber> subscribers;
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(insertable = false, updatable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address", nullable = false)
    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    @Id
    @GeneratedValue(generator = "id_addr")
    @Column(name = "address_id")
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "house")
    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Column(name = "flat")
    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

}
