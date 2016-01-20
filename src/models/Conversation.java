package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by Ekaterina on 03.03.2015.
 */
@javax.persistence.SequenceGenerator(name = "id_convers", sequenceName = "id_convers")
@Entity
@Table(name = "conversation")
public class Conversation {
    private Integer conversId;
    private String dateconvers;
    private String timeconvers;
    private Integer duration;
    private Integer costconvers;
    private Subscriber subscriber;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(insertable = false, updatable = false)
    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Id
    @GeneratedValue(generator = "id_convers")
    @Column(name = "convers_id")
    public Integer getConversId() {
        return conversId;
    }

    public void setConversId(Integer conversId) {
        this.conversId = conversId;
    }

    @Column(name = "dateconvers")
    public String getDateconvers() {
        return dateconvers;
    }

    public void setDateconvers(String dateconvers) {
        this.dateconvers = dateconvers;
    }

    @Column(name = "timeconvers")
    public String getTimeconvers() {
        return timeconvers;
    }

    public void setTimeconvers(String timeconvers) {
        this.timeconvers = timeconvers;
    }

    @Column(name = "duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Column(name = "costconvers")
    public Integer getCostconvers() {
        return costconvers;
    }

    public void setCostconvers(Integer costconvers) {
        this.costconvers = costconvers;
    }

}
