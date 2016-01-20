package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Ekaterina on 03.03.2015.
 */
@javax.persistence.SequenceGenerator(name = "id_subscr", sequenceName = "id_subscr")
@Entity
@Table(name = "subscriber")
public class Subscriber {
    private int accountNumber;
    private String name;
    private Integer tel;
    private List<Conversation> conversations;
    private Address address;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number", nullable = false)
    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(insertable = false, updatable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Id
    @GeneratedValue(generator = "id_subscr")
    @Column(name = "account_number")
    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "tel")
    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

}
