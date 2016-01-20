package models;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Ekaterina on 03.03.2015.
 */
@javax.persistence.SequenceGenerator(name = "id_city", sequenceName = "id_city")
@Entity
@Table(name = "city")
public class City {
    private Integer cityId;
    private String name;
    private Integer tarif;
    private List<Address> addresses;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Id
    @GeneratedValue(generator = "id_city")
    @Column(name = "city_id")
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "tarif")
    public Integer getTarif() {
        return tarif;
    }

    public void setTarif(Integer tarif) {
        this.tarif = tarif;
    }

}
