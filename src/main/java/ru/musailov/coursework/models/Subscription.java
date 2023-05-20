package ru.musailov.coursework.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "subscription")
@Setter
@Getter
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "subscription_type")
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date expirationDate;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonManagedReference
    private Client client;

    public Subscription(SubscriptionType subscriptionType, Date expirationDate, Client client) {
        this.subscriptionType = subscriptionType;
        this.expirationDate = expirationDate;
        this.client = client;
    }
}
